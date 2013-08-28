package com.jasty.jsp.tags;

import com.jasty.components.std.FileUpload;
import com.jasty.jsp.ComponentTag;

public class FileUploadTag extends ComponentTag<FileUpload>{
    private static final long serialVersionUID = 1L;

    public FileUploadTag() {
        super(FileUpload.class);
    }
}
