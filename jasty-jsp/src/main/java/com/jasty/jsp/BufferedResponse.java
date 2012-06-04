package com.jasty.jsp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of HttpServletResponse to render views in memory
 *
 * @author Stanislav Tkachev
 * @version 1.0
 *
 */
class BufferedResponse implements HttpServletResponse {

	private ServletResponse resp;
	private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	private StringWriter writer = new StringWriter();

	public BufferedResponse(ServletResponse resp) {
		this.resp = resp;
	}
	
	public String getContent() {
		return writer.toString();
	}
	
	@Override
	public void flushBuffer() throws IOException {
	}

	@Override
	public int getBufferSize() {
		return 0;
	}

	@Override
	public String getCharacterEncoding() {
		return resp.getCharacterEncoding();
	}

	@Override
	public String getContentType() {
		return resp.getContentType();
	}

	@Override
	public Locale getLocale() {
		return resp.getLocale();
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return new ServletOutputStream() {
			@Override
			public void write(int arg0) throws IOException {
				outputStream.write(arg0);
			}
		};
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(writer);
	}

	@Override
	public boolean isCommitted() {
		return resp.isCommitted();
	}

	@Override
	public void reset() {
	}

	@Override
	public void resetBuffer() {		
	}

	@Override
	public void setBufferSize(int arg0) {
		
	}

	@Override
	public void setCharacterEncoding(String arg0) {
	}

	@Override
	public void setContentLength(int arg0) {
	}

	@Override
	public void setContentType(String arg0) {
	}

	@Override
	public void setLocale(Locale arg0) {
	}

	@Override
	public void addCookie(Cookie arg0) {
	}

	@Override
	public void addDateHeader(String arg0, long arg1) {
	}

	@Override
	public void addHeader(String arg0, String arg1) {
	}

	@Override
	public void addIntHeader(String arg0, int arg1) {
	}

	@Override
	public boolean containsHeader(String arg0) {
		return false;
	}

	@Override
	public String encodeRedirectURL(String arg0) {
		return null;
	}

	@Override
	public String encodeRedirectUrl(String arg0) {
		return null;
	}

	@Override
	public String encodeURL(String arg0) {
		return null;
	}

	@Override
	public String encodeUrl(String arg0) {
		return null;
	}

	@Override
	public void sendError(int arg0) throws IOException {
	}

	@Override
	public void sendError(int arg0, String arg1) throws IOException {
	}

	@Override
	public void sendRedirect(String arg0) throws IOException {
	}

	@Override
	public void setDateHeader(String arg0, long arg1) {
	}

	@Override
	public void setHeader(String arg0, String arg1) {
	}

	@Override
	public void setIntHeader(String arg0, int arg1) {
	}

	@Override
	public void setStatus(int arg0) {
	}

	@Override
	public void setStatus(int arg0, String arg1) {
	}
}
