package com.easygo.common.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.View;
import org.springframework.web.util.NestedServletException;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.JSON;

public class EasyGoDispatcherServlet extends DispatcherServlet{
	private static final UrlPathHelper urlPathHelper = new UrlPathHelper();
	
	private static final long serialVersionUID = -888294923606772463L;

	public EasyGoDispatcherServlet() {
		super();
	}
	
	public EasyGoDispatcherServlet(WebApplicationContext webApplicationContext) {
		super(webApplicationContext);
	}
	
	public static boolean isAjaxRequest(HttpServletRequest request){
		if(StringUtils.equals(request.getHeader("x-requested-with"), "XMLHttpRequest")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 请求到方法的映射
	 */
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpServletRequest processedRequest = request;
		HandlerExecutionChain mappedHandler = null;
		int interceptorIndex = -1;

		try {
			ModelAndView mv = null;
			boolean errorView = false;

			try {
				processedRequest = checkMultipart(request);

				// Determine handler for the current request.
				mappedHandler = getHandler(processedRequest, false);
				if (mappedHandler == null || mappedHandler.getHandler() == null) {
					noHandlerFound(processedRequest, response);
					return;
				}

				// Determine handler adapter for the current request.
				HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());

                // Process last-modified header, if supported by the handler.
				String method = request.getMethod();
				boolean isGet = "GET".equals(method);
				if (isGet || "HEAD".equals(method)) {
					long lastModified = ha.getLastModified(request, mappedHandler.getHandler());
					if (logger.isDebugEnabled()) {
						String requestUri = urlPathHelper.getRequestUri(request);
						logger.debug("Last-Modified value for [" + requestUri + "] is: " + lastModified);
					}
					if (new ServletWebRequest(request, response).checkNotModified(lastModified) && isGet) {
						return;
					}
				}

				// Apply preHandle methods of registered interceptors.
				HandlerInterceptor[] interceptors = mappedHandler.getInterceptors();
				if (interceptors != null) {
					for (int i = 0; i < interceptors.length; i++) {
						HandlerInterceptor interceptor = interceptors[i];
						if (!interceptor.preHandle(processedRequest, response, mappedHandler.getHandler())) {
							triggerAfterCompletion(mappedHandler, interceptorIndex, processedRequest, response, null);
							return;
						}
						interceptorIndex = i;
					}
				}

				//START:controller方法try/catch，返回status、msg////////////////////////////////////
				Byte status = Constant.STATUS_ERR;
				String msg = null;
				try{
					// Actually invoke the handler.
					mv = ha.handle(processedRequest, response, mappedHandler.getHandler());
					status = Constant.STATUS_SUCC;
				}catch(Exception e){
					e.printStackTrace();
					//判断是否为ajax请求。是则将错误信息封装到modelAndView中返回，否则继续抛出异常
					if(isAjaxRequest(request)){
						msg = e.getMessage();
					}else{
						throw e;
					}
				}
				
				String viewName = mv != null && StringUtils.isNotEmpty(mv.getViewName())
						? mv.getViewName() : Constant.NULL_VIEW;
				Map<String, Object> modelMap = mv != null && mv.getModel() != null
						? mv.getModel() : new HashMap<String, Object>();
						
				modelMap.put(Constant.STATUS_NAME, status);
				modelMap.put(Constant.MSG_NAME, msg);
				mv = new ModelAndView(viewName, modelMap);
				//END:controller方法try/catch，返回status、msg////////////////////////////////////

				// Do we need view name translation?
				if (mv != null && !mv.hasView()) {
					mv.setViewName(getDefaultViewName(request));
				}

				// Apply postHandle methods of registered interceptors.
				if (interceptors != null) {
					for (int i = interceptors.length - 1; i >= 0; i--) {
						HandlerInterceptor interceptor = interceptors[i];
						interceptor.postHandle(processedRequest, response, mappedHandler.getHandler(), mv);
					}
				}
			}
			catch (ModelAndViewDefiningException ex) {
				logger.debug("ModelAndViewDefiningException encountered", ex);
				mv = ex.getModelAndView();
			}
			catch (Exception ex) {
				Object handler = (mappedHandler != null ? mappedHandler.getHandler() : null);
				mv = processHandlerException(processedRequest, response, handler, ex);
				errorView = (mv != null);
			}

			// Did the handler return a view to render?
			if (mv != null && !mv.wasCleared()) {
				render(mv, processedRequest, response);
				if (errorView) {
					WebUtils.clearErrorRequestAttributes(request);
				}
			}
			else {
				if (logger.isDebugEnabled()) {
					logger.debug("Null ModelAndView returned to DispatcherServlet with name '" + getServletName() +
							"': assuming HandlerAdapter completed request handling");
				}
			}

			// Trigger after-completion for successful outcome.
			triggerAfterCompletion(mappedHandler, interceptorIndex, processedRequest, response, null);
		}

		catch (Exception ex) {
			// Trigger after-completion for thrown exception.
			triggerAfterCompletion(mappedHandler, interceptorIndex, processedRequest, response, ex);
			throw ex;
		}
		catch (Error err) {
			ServletException ex = new NestedServletException("Handler processing failed", err);
			// Trigger after-completion for thrown exception.
			triggerAfterCompletion(mappedHandler, interceptorIndex, processedRequest, response, ex);
			throw ex;
		}

		finally {
			// Clean up any resources used by a multipart request.
			if (processedRequest != request) {
				cleanupMultipart(processedRequest);
			}
		}
	}
	
	
	private void triggerAfterCompletion(HandlerExecutionChain mappedHandler,
			int interceptorIndex,
			HttpServletRequest request,
			HttpServletResponse response,
			Exception ex) throws Exception {

		// Apply afterCompletion methods of registered interceptors.
		if (mappedHandler != null) {
			HandlerInterceptor[] interceptors = mappedHandler.getInterceptors();
			if (interceptors != null) {
				for (int i = interceptorIndex; i >= 0; i--) {
					HandlerInterceptor interceptor = interceptors[i];
					try {
						interceptor.afterCompletion(request, response, mappedHandler.getHandler(), ex);
					}
					catch (Throwable ex2) {
						logger.error("HandlerInterceptor.afterCompletion threw exception", ex2);
					}
				}
			}
		}
	}
	

	/**
	 * 根据modelAndView渲染页面
	 * 如果view名称为Constant.NULL_VIEW，则返回json数据
	 */
	protected void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(mv != null && mv.getViewName().equals(Constant.NULL_VIEW) && mv.getModel() != null){
			response.setContentType(Constant.HTTP_JSON_CONTENTTYPE);
			
			Map<String, Object> model = mv.getModel(),
					newModel = new HashMap<String, Object>();
			Set<Map.Entry<String, Object>> entrySet = model.entrySet();
			for(Map.Entry<String, Object> entry: entrySet){
				if(!StringUtils.startsWith(entry.getKey(), BindingResult.MODEL_KEY_PREFIX)){
					newModel.put(entry.getKey(), entry.getValue());
				}
			}
			
			response.getWriter().print(JSON.toJSONString(newModel));
		}else{
			super.render(mv, request, response);
		}
		
	}

}
