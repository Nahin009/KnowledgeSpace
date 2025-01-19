package org.example.quiz_service.util;

import lombok.Getter;
import lombok.Setter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

public class TextParser {

    public static List<Placeholder> extractPlaceholders(String content) {
        List<Placeholder> placeholders = new ArrayList<>();
        Pattern imagePattern = Pattern.compile("# Image Placeholder: (.+)");
        Pattern flowchartPattern = Pattern.compile("# Flowchart Placeholder:\\s*```mermaid\\s*([\\s\\S]*?)\\s*```");

        Matcher imageMatcher = imagePattern.matcher(content);
        while (imageMatcher.find()) {
            String tag = imageMatcher.group(0);
            String description = imageMatcher.group(1);
            placeholders.add(new Placeholder(tag, description, PlaceholderType.IMAGE));
        }

        Matcher flowchartMatcher = flowchartPattern.matcher(content);
        while (flowchartMatcher.find()) {
            String tag = flowchartMatcher.group(0);
            String description = flowchartMatcher.group(1);
            placeholders.add(new Placeholder(tag, description, PlaceholderType.FLOWCHART));
        }

        return placeholders;
    }

    @Getter
    @Setter
    public static class Placeholder {
        private String tag;
        private String description;
        private PlaceholderType type;

        public Placeholder(String tag, String description, PlaceholderType type) {
            this.tag = tag;
            this.description = description;
            this.type = type;
        }

    }

    public enum PlaceholderType {
        IMAGE, FLOWCHART
    }
}

