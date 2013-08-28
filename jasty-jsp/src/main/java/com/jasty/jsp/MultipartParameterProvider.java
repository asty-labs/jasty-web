package com.jasty.jsp;

import com.jasty.core.ParameterProvider;
import com.jasty.core.UploadedFile;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MultipartParameterProvider implements ParameterProvider {

    private Map<String, List<String>> fields = new HashMap<String, List<String>>();
    private Map<String, FileItem> files = new HashMap<String, FileItem>();

    public MultipartParameterProvider(HttpServletRequest request) {
        FileItemFactory factory = new DiskFileItemFactory();

        List<FileItem> fileItems;
        try {
            fileItems = new ServletFileUpload(factory).parseRequest(request);
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        }
        for(FileItem item : fileItems) {
            if(item.isFormField()) {
                String key = item.getFieldName();
                List<String> values = fields.get(key);
                if(values == null) {
                    values = new ArrayList<String>();
                    fields.put(key, values);
                }
                values.add(item.getString());
            }
            else
                files.put(item.getFieldName(), item);
        }

    }

    @Override
    public String getParameter(String name) {
        List<String> values = fields.get(name);
        if(values == null) return null;
        return values.get(0);
    }

    @Override
    public UploadedFile getFile(String name) {
        if(files.containsKey(name)) return new UploadedFileWrapper(files.get(name));
        return null;
    }

    @Override
    public Collection<String> getParameterNames() {
        return fields.keySet();
    }

    @Override
    public String[] getParameterValues(String name) {
        List<String> values = fields.get(name);
        if(values == null) return new String[0];
        return values.toArray(new String[values.size()]);
    }

    class UploadedFileWrapper implements UploadedFile {

        private FileItem file;

        public UploadedFileWrapper(FileItem file) {
            this.file = file;
        }

        @Override
        public String getName() {
            return file.getFieldName();
        }

        @Override
        public String getOriginalFilename() {
            return file.getName();
        }

        @Override
        public String getContentType() {
            return file.getContentType();
        }

        @Override
        public boolean isEmpty() {
            return "".equals(file.getName());
        }

        @Override
        public long getSize() {
            return file.getSize();
        }

        @Override
        public byte[] getBytes() throws IOException {
            return file.get();
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return file.getInputStream();
        }
    }
}
