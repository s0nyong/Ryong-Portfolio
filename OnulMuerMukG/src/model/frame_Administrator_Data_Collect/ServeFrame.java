//package model.frame_Administrator_Data_Collect;
//
//import model.Datasource;
//import model.FrameSize;
//import model.PrintFrame;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
////public class ServeFrame{
////
////    public void menu(){
//
////        JFrame sfrm = new JFrame();
//public class ServeFrame extends JFrame implements FrameSize {
//
//
//    public ServeFrame(String user_id){
//
//        setTitle("서브프레임");
//        setSize(FrameWidth, FrameHeight);
//        setLocationRelativeTo(null); //화면 가운데 배치
//        setResizable(false); //크기 변경 불가능
//        getContentPane().setLayout(null); //레이아웃 설정
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JLabel jl = new JLabel("텍스트를 출력중입니다");
//        JButton jb = new JButton("매뉴닫기");
//
//        jb.setBounds(220,400,80,40);
//        jl.setBounds(220,450,80,40);
//
//        add(jb);
//        add(jl);
//
//        ActionListener jb_action = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JButton button = (JButton)e.getSource();
//                setVisible(false);
//                PrintFrame printframe = new PrintFrame();
//                Datasource datasource = new Datasource();
//                datasource.open();
//                printframe.OutputFrame(datasource.getConn(), user_id);
//            }
//        };
//        jb.addActionListener(jb_action);
//
//
//        setVisible(true);
//
//    }
//}
