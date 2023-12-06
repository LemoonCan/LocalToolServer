package lemoon.can.toolserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * @author lee
 * @since 2023/12/4
 */
@Controller
@RequestMapping(value = "/cmd")
public class LocalCommand {
    private final static String errorPage = "error";
    private final static String closePage = "close";

    /**
     * 打开本地文件(通过执行open命令实现)
     *
     * @param path         文件路径
     * @param model        模型
     * @return 视图
     */
    @GetMapping(value = "/openFile")
    public String openFile(@RequestParam String path, Model model) {
        Process process;
        //自定义本地根目录(若想控制访问根目录，可在此设置)
        String directory = "";
        path = directory + path;
        if (path.endsWith("xmind")) {
            try {

                process = new ProcessBuilder("open", "-a", "xmind", path)
                        .start();
            } catch (IOException e) {
                model.addAttribute("errorMessage", e.getMessage());
                return errorPage;
            }
            try {
                process.waitFor();
            } catch (InterruptedException e) {
                model.addAttribute("errorMessage", e.getMessage());
                return errorPage;
            }
            process.destroy();

            return closePage;
        }
        model.addAttribute("errorMessage", "不支持的文件类型");
        return errorPage;
    }
}
