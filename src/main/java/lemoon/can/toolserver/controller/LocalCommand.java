package lemoon.can.toolserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;

/**
 * @author lee
 * @since 2023/12/4
 */
@Controller
@RequestMapping(value="/cmd")
public class LocalCommand {

    /**
     * 打开本地文件(通过执行open命令实现)
     * @param path
     * @return
     */
    @GetMapping(value = "/openFile")
    public String openFile(@RequestParam String path){
        Process process;
        //自定义本地根目录(若想控制访问根目录，可在此设置)
        String directory = "";
        path = directory + path;
        try {
            process = new ProcessBuilder("open", path)
                    .start();
        } catch (IOException e) {
            return e.getMessage();
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            return e.getMessage();
        }
        process.destroy();

        return "closePage";
    }
}
