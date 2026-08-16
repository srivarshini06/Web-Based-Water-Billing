package com.water.backend.util;

import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public final class CSVUploadUtil {
    private CSVUploadUtil() {}
    public static Map<String,Object> parseCSV(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) throw new IllegalArgumentException("CSV file is empty");
        List<Map<String,String>> rows = new ArrayList<>(); List<String> errors = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String headerLine = br.readLine();
            if (headerLine == null) throw new IllegalArgumentException("CSV file is empty");
            List<String> headers = split(headerLine);
            Set<String> required = Set.of("residentId","readingDate","previousReading","currentReading");
            for (String h : required) if (!headers.contains(h)) errors.add("Missing required column: " + h);
            if (!errors.isEmpty()) return Map.of("rows", rows, "errors", errors);
            String line; int lineNo=1;
            while ((line=br.readLine()) != null) { lineNo++; if (line.isBlank()) continue; List<String> vals=split(line); if(vals.size()!=headers.size()){errors.add("Line "+lineNo+": Column count mismatch"); continue;} Map<String,String> row=new LinkedHashMap<>(); for(int i=0;i<headers.size();i++) row.put(headers.get(i).trim(), vals.get(i).trim()); rows.add(row); }
        }
        return Map.of("rows", rows, "errors", errors);
    }
    private static List<String> split(String line) {
        List<String> out=new ArrayList<>(); StringBuilder cur=new StringBuilder(); boolean quoted=false;
        for(int i=0;i<line.length();i++){char c=line.charAt(i); if(c=='"'){ if(quoted && i+1<line.length() && line.charAt(i+1)=='"'){cur.append('"');i++;} else quoted=!quoted; } else if(c==',' && !quoted){out.add(cur.toString());cur.setLength(0);} else cur.append(c);}
        if(quoted) throw new IllegalArgumentException("Unclosed quoted field"); out.add(cur.toString()); return out;
    }
}
