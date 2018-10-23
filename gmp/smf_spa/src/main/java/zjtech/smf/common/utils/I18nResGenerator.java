package zjtech.smf.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * 将国际化资源转换并生成客户端JS代码
 */
public class I18nResGenerator {

    public static void main(String[] args) {
        I18nResGenerator generator = new I18nResGenerator();
        generator.init();
        generator.start();
    }

    private List<FileDef> mappings = new ArrayList<>();

    public static class FileDef {
        private String resPath;
        private String jsPath;
        private boolean root;

        public FileDef(String resPath,
                       String jsPath,
                       boolean root) {
            this.resPath = resPath;
            this.jsPath = jsPath;
            this.root = root;
        }

        public String getResPath() {
            return resPath;
        }

        public void setResPath(String resPath) {
            this.resPath = resPath;
        }

        public String getJsPath() {
            return jsPath;
        }

        public void setJsPath(String jsPath) {
            this.jsPath = jsPath;
        }

        public boolean isRoot() {
            return root;
        }

        public void setRoot(boolean isRoot) {
            this.root = isRoot;
        }
    }

    public void init() {
        mappings.add(new FileDef("/opt/iprojects/gdp/gmp/smf_spa/src/main/resources/locale/res_zh_CN.properties",
                "/opt/iprojects/gdp/gmp/smf_spa/src/main/webapp/spa2/nls/zh-CN/res.js", false));
        mappings.add(new FileDef("/opt/iprojects/gdp/gmp/smf_spa/src/main/resources/locale/res_en_US.properties",
                "/opt/iprojects/gdp/gmp/smf_spa/src/main/webapp/spa2/nls/res.js", true));
    }

    public void start() {
        mappings.forEach((fileDef) -> {
            Properties properties = new Properties();
            PrintWriter pw = null;
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileDef.getResPath()), StandardCharsets.UTF_8);
                 BufferedWriter bw = Files.newBufferedWriter(Paths.get(fileDef.getJsPath()))) {
                properties.load(reader);
                pw = new PrintWriter(bw);
                pw.println("define({");

                String bck = "    ";
                if (fileDef.isRoot()) {
                    pw.write("    \"root\": {");
                    pw.println();
                    bck += "    ";
                }

                int size = properties.size();
                int index = 0;
                Set<Map.Entry<Object, Object>> entries = properties.entrySet();
                for (Map.Entry<Object, Object> entry : entries) {
                    pw.write(bck);

                    String val = entry.getValue().toString().trim();
                    boolean wrapVal = true;
                    if (val.startsWith("[") && val.endsWith("]")) {
                        val = val.substring(1, val.length() - 1);
                        wrapVal = false;
                    }

                    if (wrapVal) {
                        pw.write("\"" + entry.getKey() + "\": \"" + val + "\"");
                    } else {
                        pw.write("\"" + entry.getKey() + "\": " + val);
                    }

                    if (index < size - 1) {
                        pw.println(",");
                    }
                    index++;
                }

                if (fileDef.isRoot()) {
                    pw.println();
                    pw.println("    },");
                    pw.write("    \"zh-CN\": true");
                    pw.println();
                }

                pw.println();
                pw.write("});");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
