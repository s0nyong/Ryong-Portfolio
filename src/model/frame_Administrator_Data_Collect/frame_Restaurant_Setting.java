package model.frame_Administrator_Data_Collect;

import model.administrator;
import model.administrator_data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class frame_Restaurant_Setting extends JFrame {

    //    JButton[] button = new JButton[5];

    public void frame_Restaurant_Setting_Method(String restaurant_Name,int restaurant_Id, Connection con,String type) {
        setSize(300, 400);
        setTitle("Administrator_Restaurant_Setting");
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);
        administrator_data ad_data = new administrator_data();


        JLabel setting_mod = new JLabel(restaurant_Name + " 음식점 관리 모드입니다");
        JLabel select = new JLabel("사용할 기능을 선택하세요.");

        JButton button1 = new JButton("1. 메뉴 보기");
        JButton button2 = new JButton("2. 메뉴 추가");
        JButton button3 = new JButton("3. 메뉴 삭제");
        JButton button4 = new JButton("4. 음식점 폐점");
        JButton button5 = new JButton("5. 이전으로 돌아가기");
        setting_mod.setBounds(10, 00, 500, 40);
        select.setBounds(10, 20, 500, 40);
//        TextField tf = new TextField("아이디 입력", 12);
//        JLabel jl = new JLabel("텍스트를 출력중입니다");
//        JButton jb = new JButton("매뉴닫기");
//
//        tf.setBounds(120, 120, 90, 30);
//        jl.setBounds(120, 160,90, 30);
//        jb.setBounds(220,0,80,40);
//
//        add(tf);
//        add(jl);
//        add(jb);

//        button[0].setText("1. 메뉴 보기");
//        button[1].setText("2. 메뉴 추가");
//        button[2].setText("3. 메뉴 삭제");
//        button[3].setText("4. 음식점 폐점");
//        button[4].setText("5. 이전으로 돌아가기");
//
//        for (int i = 0; i < button.length; i++) {
//            button[i].setBounds(10, 60 + i*20, 100, 40);
//            p.add(button[i]);
//        }

        button1.setBounds(10, 50, 150, 30);
        button2.setBounds(10, 90, 150, 30);
        button3.setBounds(10, 130, 150, 30);
        button4.setBounds(10, 170, 150, 30);
        button5.setBounds(10, 210, 150, 30);

        add(setting_mod);
        add(select);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        setVisible(true);
        ActionListener action1 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //ad_data.select_this_restaurant_menu(restaurant_Id, con);
                frame_Select_This_Restaurant_menu fstrm = new frame_Select_This_Restaurant_menu();
                fstrm.frame_Select_This_Restaurant_menu_method(restaurant_Name, restaurant_Id, con, type);
            }
        };
        button1.addActionListener(action1);
//        ActionListener jb_action = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JButton button = (JButton)e.getSource();
//                setVisible(false);
//            }
//        };
//        jb.addActionListener(jb_action);
//        administrator_data ad_data = new administrator_data();
//        switch (num) {
//            case 0:
//                ad_data.select_this_restaurant_menu(1, con);
//                break;
//            case 1:
//                ad_data.menu_Add(1, con);
//                break;
//            case 2:
//                ad_data.menu_Delete(1, con);
//                break;
//            case 3:
//                ad_data.restaurant_Delete(restaurant_Name, con);
//                return;
//            case 4:
//
//        }
        ActionListener action2 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
//                        ad_data.menu_Add(restaurant_Id, con);
                frame_Menu_Add fma = new frame_Menu_Add();
                fma.frame_Menu_Add_Method(restaurant_Name, restaurant_Id, con, type);
            }
        };
        button2.addActionListener(action2);

        ActionListener action3 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                frame_Menu_Delete fmd= new frame_Menu_Delete();
                fmd.frame_Menu_Delete_Method(restaurant_Name, restaurant_Id, con, type);
            }
        };
        button3.addActionListener(action3);

        ActionListener action4 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ad_data.restaurant_Delete(restaurant_Name, con);
            }
        };
        button4.addActionListener(action4);

        ActionListener action5 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                administrator.administrator_Main(con, type);
            }
        };
        button5.addActionListener(action5);
    }
//    @Override
//    public void actionPerformed(ActionEvent e) {
//            administrator_data ad_data = new administrator_data();
//            Datasource data = new Datasource();
//            if (data.open()) {
//                if (e.getSource() == button1)
//                    ad_data.select_this_restaurant_menu(1, data.getConn());
//                if (e.getSource() == button2)
//                    ad_data.menu_Add(1, data.getConn());
//                if (e.getSource() == button3)
//                    ad_data.menu_Delete(1, data.getConn());
//                if (e.getSource() == button4)
//                    ad_data.restaurant_Delete("restaurant name", data.getConn());
//                if (e.getSource() == button5)
//                    administrator.administrator_Main(data.getConn());
//            }
//        }
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == button1)
//            num = 0;
//        if (e.getSource() == button2)
//            num = 1;
//        if (e.getSource() == button3)
//            num = 2;
//        if (e.getSource() == button4)
//            num = 3;
//        if (e.getSource() == button5)
//            num = 4;
//    }
}

