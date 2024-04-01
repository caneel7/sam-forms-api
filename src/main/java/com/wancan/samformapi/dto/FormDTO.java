package com.wancan.samformapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class FormDTO {

    private String type;
    private String label;
    private String placeholder;
    private Object width;
    private Object style;
    private Object validations;


    public class FormWidth{
        private String xs;
        private String sm;
        private String md;
        private String lg;
    }


    private class FormStyle{
        private String backgroundColor;
        private String border;
        private String padding;
        private String margin;
        private String font;
        private String fontSize;
        private String fontWeight;
        private String color;
        private String width;
        private String height;
        private String textAlign;
        private String textTransform;
        private String textDecoration;
        private String boxShadow;
        private String outline;
    }

    private class FormValidations{
        private boolean required;
        private String pattern;
    }

    public class FormResponse{
        private String label;
        private String answer;
    }
}
