package edu.project3;

import java.awt.desktop.OpenURIEvent;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class WriteMD {
    static int COL_LENGTH = 30;
    PrintWriter out = null;
    public WriteMD title(String title) {
        out.println("#### " + title);
        out.println();
        return this;
    }
    String rowView(List<String> row){
        StringBuilder builder = new StringBuilder("|");
        for (String col : row) {
//            String space = " ".repeat((COL_LENGTH - col.length()) / 2);
            builder
//                .append(space)
                .append(col)
//                .append(space)
                .append("|");
        }
        return new String(builder);
    };
    public WriteMD grid(List<String> headers, List<List<List<String>>> rowColValues) {
        out.println(rowView(headers));
        out.println("|" + "----------|".repeat(headers.size()));
        for (var row : rowColValues) {
            int maxLength = 0;
            for (var col : row) {
                maxLength = Math.max(maxLength, col.size());
            }
            for (int i = 0; i < maxLength; ++i) {
                List<String> rowToAdd = new ArrayList<>(row.size());
                for (var col : row) {
                    rowToAdd.add(col.size() > i? col.get(i) : "");
                }
                out.println(rowView(rowToAdd));
            }
        }
        out.println();
        return this;

    }

    public WriteMD setWriter(PrintWriter writer) {
        out = writer;
        return this;
    }
}
