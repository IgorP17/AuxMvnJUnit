package org.ljv;

import org.atpfivt.ljv.LJV;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        browse(new LJV(), Map.of(1, 'a', 2, 'b'));
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(3);
        list1.add(2);
        browse(new LJV(), list1);
        List<Integer> list2 = new LinkedList<>();
        list2.add(1);
        list2.add(3);
        list2.add(2);
        browse(new LJV(), list2);

    }

    public static void browse(LJV ljv, Object obj) {
        try {
            var dot = URLEncoder.encode(ljv.drawGraph(obj), StandardCharsets.UTF_8)
                    .replaceAll("\\+", "%20");
            System.out.println(dot);
            System.out.println("Please open: \n" + "https://dreampuf.github.io/GraphvizOnline/#" + dot);
            printToFile(ljv.drawGraph(obj));
            /*Desktop.getDesktop().browse(
                    new URI("https://dreampuf.github.io/GraphvizOnline/#"
                            + dot));*/
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    // Graphviz - https://graphviz.org/download/
    // dot -Tpng myfile.dot > myfile.png
    private static void printToFile(String dot){
        File file = new File("dotfile.dot");
        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.println(dot);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
