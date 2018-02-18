package com.company;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://fredagscafeen.dk/prices/");
            BufferedReader s = new BufferedReader(new InputStreamReader(url.openStream()));
            ArrayList<String> site = new ArrayList<>();
            String inputline = null;
            while((inputline = s.readLine()) != null){
                site.add(inputline);
            }
            s.close();
            ArrayList<String> beers = new ArrayList<>(site.stream()
                    .filter(str -> str.contains("<td>"))
                    .filter(str -> !str.matches(".*\\d+,\\d+.*"))
                    .collect(Collectors.toList()));
            for (int i = 0; i < beers.size(); i++) {
                beers.set(i,beers.get(i).replaceAll(".*<td>","").replaceAll("</td>.*",""));
                System.out.println(beers.get(i));
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
