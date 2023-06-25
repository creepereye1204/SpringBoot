package com.example.controller;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;

@Controller
public class download {

    @GetMapping("/")
    public String home(){


        return "home";
    }
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws Exception{
        try{
            final String path="절대경로";
            File file= new File(path);
            byte[] fileByte = Files.readAllBytes(file.toPath());
            //파일을 바이너리로 불러오기

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(file.getName(),"UTF-8")+";");
            //"application/octet-stream" 이진파일을 위한 기본값
            //"Content-Disposition" => 다운로드 모드로 세팅,업로드 모드도 세팅가능
            //"attachment;filename=" => 파일이름을 UTF-8형식으로 설정

            response.setHeader("Content-Transfer-Encoding","binary");
            //헤더를 바이너리 형식으로 변경
            response.getOutputStream().write(fileByte);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }catch (Exception e){
            throw new Exception("download error");
        }
    }
}
