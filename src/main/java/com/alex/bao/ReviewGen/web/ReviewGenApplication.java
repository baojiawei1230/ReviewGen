//package com.alex.bao.ReviewGen.web;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.util.Properties;
//import java.util.concurrent.TimeUnit;
//
///**
// * ReviewId生成器
// *
// * @Author Alex_Bao
// * @create 2017-09-12
// * create by IntelliJ IDEA
// */
//
//@SpringBootApplication
//@Controller
//@RequestMapping("/review")
//public class ReviewGenApplication {
//
//    @Value("${linux.path}")
//    private String linuxPath;
//    @Value("${win.path}")
//    private String winPath;
//
//    public static void main(String[] args) {
//        SpringApplication.run(ReviewGenApplication.class, args);
//    }
//
//    @RequestMapping("/index")
//    public String index() {
//        return "review";
//    }
//
//    @RequestMapping("/generateID")
//    public void generateID(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            String[] command = null;
//            String osName = System.getProperty("os.name");
//            if (osName != null && osName.trim().length() > 0) {
//                if (osName.toLowerCase().contains("windows")) {
//                    //windows
//                    command = getExecCommandForWin();
//                } else {
//                    //linux osx
//                    command = getExecCommandForLinux();
//                }
//            }
//            //execute the command
//            if (command == null || command.length == 0) {
//                throw new IllegalArgumentException(" command must not be null ");
//            }
//            Process p = Runtime.getRuntime().exec(command);
//            BufferedReader bufferedReader = null;
//            if (p != null) {
//                BufferedInputStream bufferedInputStream = new BufferedInputStream(p.getInputStream());
//                bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
//            }
//            String readLine;
//            String result = "";
//            while ((readLine = bufferedReader.readLine()) != null) {
//                result += readLine;
//            }
//            bufferedReader.close();
//            bufferedReader.close();
//            //输出结果.
//            System.out.println("reviewId : " + result);
//            result = "{\"result\":\"" + result + "\"}";
//            this.sendAjaxResponse(result, response);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * get command by os type
//     *
//     * @return
//     */
//    private String[] getExecCommandForLinux() {
//        String linuxPath = this.getPathByType("LINUX");
//        if (linuxPath == null || linuxPath.length() == 0) {
//            throw new IllegalArgumentException(" linux path must not be null ");
//        }
//        String[] linuxCommand = new String[]{"python", linuxPath};
//        return linuxCommand;
//    }
//
//    /**
//     * get command by os type
//     *
//     * @return
//     */
//    private String[] getExecCommandForWin() {
//        String winPath = this.getPathByType("WIN");
//        String[] command = new String[]{"cmd", "/c", "start", "python", winPath};
//        return command;
//    }
//
//    /**
//     * getPathByType
//     *
//     * @param osType
//     * @return
//     */
//    private String getPathByType(String osType) {
//        String execPath = null;
//        if (osType.equals("WIN")) {
//            execPath = winPath;
//        } else {
//            execPath = linuxPath;
//        }
//        if (execPath == null || execPath.trim().length() == 0) {
//            throw new IllegalArgumentException(" exec-path must not be null !");
//        }
//        return execPath;
//    }
//
//    /**
//     * 发送ajax结果
//     *
//     * @param result
//     * @param response
//     */
//    private void sendAjaxResponse(String result, HttpServletResponse response) {
//        response.setContentType("application/json;charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        try {
//            PrintWriter writer = response.getWriter();
//            writer.write(result);
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
