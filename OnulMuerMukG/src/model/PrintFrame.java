package model;

import model.ArrayList_Collect.Output;
import model.frame_Owner_Collect.frame_Owner_Main;
import model.frame_User_Collect.frame_User_Rap_Check;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;
//AWT(Abstract Window Toolkit) //OS종속적

public class PrintFrame implements FrameSize{
    boolean b11 = true, b12, b13, b14, b15, b16;
    boolean b21 = true, b22, b23, b24, b25, b26, b27, b28;
    boolean b31, b32, b33, b34, b35, b36= true;

    Boolean[][] booleans = {{false},
            {false, b11, b12, b13, b14, b15, b16},
            {false, b21, b22, b23, b24, b25, b26, b27, b28},
            {false, b31, b32 ,b33 , b34, b35, b36}};

//    부모 프레임 생성 및 설정
//   > 자식 컴포넌트 생성 및 설정
//   > 자식 컴포넌트 이벤트 정의
//   > 부모 프레임에 자식 컴포넌트 추가
//   > 부모 프레임을 보이게함

    public void OutputFrame(String user_name,Connection con, String type) {

        JFrame frm = new JFrame();
        frm.setTitle("오늘 뭐 먹지");
        frm.setSize(FrameWidth, FrameHeight);
        frm.setLocationRelativeTo(null); //화면 가운데 배치
        frm.setResizable(false); //크기 변경 불가능
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //윈도우의 x를 누르면 종료
        frm.getContentPane().setLayout(null); //배치관리자 종료

        JButton btn00 = new JButton("#");
        JButton btn11 = new JButton("전체");
        JButton btn12 = new JButton("한식");
        JButton btn13 = new JButton("일식");
        JButton btn14 = new JButton("양식");
        JButton btn15 = new JButton("중식");
        JButton btn16 = new JButton("기타");
        JButton btn21 = new JButton("전체");
        JButton btn22 = new JButton("쌀/밥");
        JButton btn23 = new JButton("면요리");
        JButton btn24 = new JButton("국/탕");
        JButton btn25 = new JButton("고기");
        JButton btn26 = new JButton("채식");
        JButton btn27 = new JButton("디저트");
        JButton btn28 = new JButton("기타");
        JButton btn31 = new JButton("300M");
        JButton btn32 = new JButton("500M");
        JButton btn33 = new JButton("1KM");
        JButton btn34 = new JButton("5KM");
        JButton btn35 = new JButton("10KM");
        JButton btn36 = new JButton("최대");
        JButton btn41 = new JButton("음식점 추천");
        JLabel txt1 = new JLabel();
        JLabel txt2 = new JLabel();
        JLabel txt3 = new JLabel();
        JLabel txt4 = new JLabel();
        JLabel txt5 = new JLabel();
        JLabel txtnull = new JLabel("   결과 창");

        //setBounds(가로위치, 세로위치, 가로길이, 세로길이);
        Font font = new Font("맑은 고딕", Font.BOLD | Font.ITALIC, TxtWidth);
        txt1.setBounds(TxtWidth, TxtHeight*1, TxtWidth*24, TxtHeight);
        txt2.setBounds(TxtWidth, TxtHeight*2, TxtWidth*24, TxtHeight);
        txt3.setBounds(TxtWidth, TxtHeight*3, TxtWidth*24, TxtHeight);
        txt4.setBounds(TxtWidth, TxtHeight*4, TxtWidth*24, TxtHeight);
        txt5.setBounds(TxtWidth, TxtHeight*5, TxtWidth*24, TxtHeight);
        txt1.setFont(font);
        txt2.setFont(font);
        txt3.setFont(font);
        txt4.setFont(font);
        txt5.setFont(font);
        txtnull.setBounds(TxtWidth*3, TxtHeight*3, TxtWidth*24, TxtHeight);
        txtnull.setFont(new Font("맑은 고딕", Font.BOLD, TxtWidth+10));

        Color color = new Color(0, 162, 232);

        btn00.setBounds(FrameWidth-ButtonWidth*1, BlankHeight, ButtonHeight, ButtonHeight);
        btn11.setBounds(BlankWidth+(ButtonWidth*0), BlankHeight*6+(ButtonHeight*0), ButtonWidth, ButtonHeight);
        btn11.setBackground(color);
        btn12.setBounds(BlankWidth+ButtonWidth*1, BlankHeight*6+(ButtonHeight*0), ButtonWidth, ButtonHeight);
        btn13.setBounds(BlankWidth+ButtonWidth*2, BlankHeight*6+(ButtonHeight*0), ButtonWidth, ButtonHeight);
        btn14.setBounds(BlankWidth+(ButtonWidth*0), BlankHeight*6+ButtonHeight, ButtonWidth, ButtonHeight);
        btn15.setBounds(BlankWidth+ButtonWidth*1, BlankHeight*6+ButtonHeight, ButtonWidth, ButtonHeight);
        btn16.setBounds(BlankWidth+ButtonWidth*2, BlankHeight*6+ButtonHeight, ButtonWidth, ButtonHeight);
        btn21.setBounds(BlankWidth+(ButtonWidth*0), BlankHeight*7+ButtonHeight*2, ButtonWidth, ButtonHeight);
        btn21.setBackground(color);
        btn22.setBounds(BlankWidth+ButtonWidth*1, BlankHeight*7+ButtonHeight*2, ButtonWidth, ButtonHeight);
        btn23.setBounds(BlankWidth+ButtonWidth*2, BlankHeight*7+ButtonHeight*2, ButtonWidth, ButtonHeight);
        btn24.setBounds(BlankWidth+(ButtonWidth*0), BlankHeight*7+ButtonHeight*3, ButtonWidth, ButtonHeight);
        btn25.setBounds(BlankWidth+ButtonWidth*1, BlankHeight*7+ButtonHeight*3, ButtonWidth, ButtonHeight);
        btn26.setBounds(BlankWidth+ButtonWidth*2, BlankHeight*7+ButtonHeight*3, ButtonWidth, ButtonHeight);
        btn27.setBounds(BlankWidth+(ButtonWidth*0), BlankHeight*7+ButtonHeight*4, ButtonWidth, ButtonHeight);
        btn28.setBounds(BlankWidth+ButtonWidth*1, BlankHeight*7+ButtonHeight*4, ButtonWidth, ButtonHeight);
        btn31.setBounds(BlankWidth+(ButtonWidth*0), BlankHeight*8+ButtonHeight*5, ButtonWidth, ButtonHeight);
        btn32.setBounds(BlankWidth+ButtonWidth*1, BlankHeight*8+ButtonHeight*5, ButtonWidth, ButtonHeight);
        btn33.setBounds(BlankWidth+ButtonWidth*2, BlankHeight*8+ButtonHeight*5, ButtonWidth, ButtonHeight);
        btn34.setBounds(BlankWidth+(ButtonWidth*0), BlankHeight*8+ButtonHeight*6, ButtonWidth, ButtonHeight);
        btn35.setBounds(BlankWidth+ButtonWidth*1, BlankHeight*8+ButtonHeight*6, ButtonWidth, ButtonHeight);
        btn36.setBounds(BlankWidth+ButtonWidth*2, BlankHeight*8+ButtonHeight*6, ButtonWidth, ButtonHeight);
        btn36.setBackground(color);
        btn41.setBounds(FrameWidth/2-ButtonWidth, BlankHeight*9+ButtonHeight*7, ButtonWidth*2, ButtonHeight*2);
        btn41.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 35));
        btn41.setHorizontalAlignment(JLabel.CENTER); //텍스트 센터 표시 설정

        ActionListener btn00_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton)e.getSource();
                if(type.equals("Admin")) {
                    administrator.administrator_Main(con,type);
                }else if(type.equals("Owner")) {
                    frame_Owner_Main fom = new  frame_Owner_Main();
                    fom.frame_Owner_Main_Mathod(user_name,con,type);
                }else if (type.equals("User")){
                    frame_User_Rap_Check furc = new frame_User_Rap_Check();
                    furc.frame_User_Rap_Check_Mathod(user_name, con, type);
                }
            }
        };
        btn00.addActionListener(btn00_action);

        ActionListener btn11_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getBackground().equals(color)) {
                    if (!b12 && !b13 && !b14 && !b15 && !b16)
                        return;
                    button.setBackground(null);
                    b11 = false;
                    booleans[1][1] = false;

                } else {
                    button.setBackground(color);
                    b11 = true;
                    b12 = false;
                    b13 = false;
                    b14 = false;
                    b15 = false;
                    b16 = false;
                    booleans[1][1] = true;
                    booleans[1][2] = false;
                    booleans[1][3] = false;
                    booleans[1][4] = false;
                    booleans[1][5] = false;
                    booleans[1][6] = false;
                    btn12.setBackground(null);
                    btn13.setBackground(null);
                    btn14.setBackground(null);
                    btn15.setBackground(null);
                    btn16.setBackground(null);
                }
            }
        };
        btn11.addActionListener(btn11_action);

        ActionListener btn12_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (b11) {
                    btn11.setBackground(null);
                    b11 = false;
                    booleans[1][1] = false;
                }
                if (button.getBackground().equals(color)) {
                    if (!b11 && !b13 && !b14 && !b15 && !b16)
                        return;
                    button.setBackground(null);
                    b12 = false;
                    booleans[1][2] = false;
                } else {
                    button.setBackground(color);
                    b12 = true;
                    booleans[1][2] = true;
                }
            }
        };
        btn12.addActionListener(btn12_action);

        ActionListener btn13_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (b11) {
                    btn11.setBackground(null);
                    b11 = false;
                    booleans[1][1] = false;
                }
                if (button.getBackground().equals(color)) {
                    if (!b11 && !b12 && !b14 && !b15 && !b16)
                        return;
                    button.setBackground(null);
                    b13 = false;
                    booleans[1][3] = false;
                } else {
                    button.setBackground(color);
                    b13 = true;
                    booleans[1][3] = true;
                }
            }
        };
        btn13.addActionListener(btn13_action);

        ActionListener btn14_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (b11) {
                    btn11.setBackground(null);
                    b11 = false;
                    booleans[1][1] = false;
                }
                if (button.getBackground().equals(color)) {
                    if (!b11 && !b12 && !b13 && !b15 && !b16)
                        return;
                    button.setBackground(null);
                    b14 = false;
                    booleans[1][4] = false;
                } else {
                    button.setBackground(color);
                    b14 = true;
                    booleans[1][4] = true;
                }
            }
        };
        btn14.addActionListener(btn14_action);

        ActionListener btn15_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (b11) {
                    btn11.setBackground(null);
                    b11 = false;
                    booleans[1][1] = false;
                }
                if (button.getBackground().equals(color)) {
                    if (!b11 && !b12 && !b13 && !b14 && !b16)
                        return;
                    button.setBackground(null);
                    b15 = false;
                    booleans[1][5] = false;
                } else {
                    button.setBackground(color);
                    b15 = true;
                    booleans[1][5] = true;
                }
            }
        };
        btn15.addActionListener(btn15_action);

        ActionListener btn16_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (b11) {
                    btn11.setBackground(null);
                    b11 = false;
                    booleans[1][1] = false;
                }
                if (button.getBackground().equals(color)) {
                    if (!b11 && !b12 && !b13 && !b14 && !b15)
                        return;
                    button.setBackground(null);
                    b16 = false;
                    booleans[1][6] = false;
                } else {
                    button.setBackground(color);
                    b16 = true;
                    booleans[1][6] = true;
                }
            }
        };
        btn16.addActionListener(btn16_action);

        ActionListener btn21_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getBackground().equals(color)) {
                    if (!b22 && !b23 && !b24 && !b25 && !b26 && !b27 && !b28)
                        return;
                    button.setBackground(null);
                    b21 = false;
                    booleans[2][1] = false;
                } else {
                    button.setBackground(color);
                    b21 = true;
                    b22 = false;
                    b23 = false;
                    b24 = false;
                    b25 = false;
                    b26 = false;
                    b27 = false;
                    b28 = false;
                    booleans[2][1] = true;
                    booleans[2][2] = false;
                    booleans[2][3] = false;
                    booleans[2][4] = false;
                    booleans[2][5] = false;
                    booleans[2][6] = false;
                    booleans[2][7] = false;
                    booleans[2][8] = false;
                    btn22.setBackground(null);
                    btn23.setBackground(null);
                    btn24.setBackground(null);
                    btn25.setBackground(null);
                    btn26.setBackground(null);
                    btn27.setBackground(null);
                    btn28.setBackground(null);
                }
            }
        };
        btn21.addActionListener(btn21_action);

        ActionListener btn22_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (b21) {
                    btn21.setBackground(null);
                    b21 = false;
                    booleans[2][1] = false;
                }
                if (button.getBackground().equals(color)) {
                    if (!b21 && !b23 && !b24 && !b25 && !b26 && !b27 && !b28)
                        return;
                    button.setBackground(null);
                    b22 = false;
                    booleans[2][2] = false;
                } else {
                    button.setBackground(color);
                    b22 = true;
                    booleans[2][2] = true;
                }
            }
        };
        btn22.addActionListener(btn22_action);

        ActionListener btn23_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (b21) {
                    btn21.setBackground(null);
                    b21 = false;
                    booleans[2][1] = false;
                }
                if (button.getBackground().equals(color)) {
                    if (!b21 && !b22 && !b24 && !b25 && !b26 && !b27 && !b28)
                        return;
                    button.setBackground(null);
                    b23 = false;
                    booleans[2][3] = false;
                } else {
                    button.setBackground(color);
                    b23 = true;
                    booleans[2][3] = true;
                }
            }
        };
        btn23.addActionListener(btn23_action);

        ActionListener btn24_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (b21) {
                    btn21.setBackground(null);
                    b21 = false;
                    booleans[2][1] = false;
                }
                if (button.getBackground().equals(color)) {
                    if (!b21 && !b22 && !b23 && !b25 && !b26 && !b27 && !b28)
                        return;
                    button.setBackground(null);
                    b24 = false;
                    booleans[2][4] = false;
                } else {
                    button.setBackground(color);
                    b24 = true;
                    booleans[2][4] = true;
                }
            }
        };
        btn24.addActionListener(btn24_action);

        ActionListener btn25_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (b21) {
                    btn21.setBackground(null);
                    b21 = false;
                    booleans[2][1] = false;
                }
                if (button.getBackground().equals(color)) {
                    if (!b21 && !b22 && !b23 && !b24 && !b26 && !b27 && !b28)
                        return;
                    button.setBackground(null);
                    b25 = false;
                    booleans[2][5] = false;
                } else {
                    button.setBackground(color);
                    b25 = true;
                    booleans[2][5] = true;
                }
            }
        };
        btn25.addActionListener(btn25_action);

        ActionListener btn26_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (b21) {
                    btn21.setBackground(null);
                    b21 = false;
                    booleans[2][1] = false;
                }
                if (button.getBackground().equals(color)) {
                    if (!b21 && !b22 && !b23 && !b24 && !b25 && !b27 && !b28)
                        return;
                    button.setBackground(null);
                    b26 = false;
                    booleans[2][6] = false;
                } else {
                    button.setBackground(color);
                    b26 = true;
                    booleans[2][6] = true;
                }
            }
        };
        btn26.addActionListener(btn26_action);

        ActionListener btn27_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (b21) {
                    btn21.setBackground(null);
                    b21 = false;
                    booleans[2][1] = false;
                }
                if (button.getBackground().equals(color)) {
                    if (!b21 && !b22 && !b23 && !b24 && !b25 && !b26 && !b28)
                        return;
                    button.setBackground(null);
                    b27 = false;
                    booleans[2][7] = false;
                } else {
                    button.setBackground(color);
                    b27 = true;
                    booleans[2][7] = true;
                }
            }
        };
        btn27.addActionListener(btn27_action);

        ActionListener btn28_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (b21) {
                    btn21.setBackground(null);
                    b21 = false;
                    booleans[2][1] = false;
                }
                if (button.getBackground().equals(color)) {
                    if (!b21 && !b22 && !b23 && !b24 && !b25 && !b26 && !b27)
                        return;
                    button.setBackground(null);
                    b28 = false;
                    booleans[2][8] = false;
                } else {
                    button.setBackground(color);
                    b28 = true;
                    booleans[2][8] = true;
                }
            }
        };
        btn28.addActionListener(btn28_action);

        ActionListener btn31_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getBackground().equals(color)) {
                    if (!b32 && !b33 && !b34 && !b35 && !b36)
                        return;
                    button.setBackground(null);
                    b31 = false;
                    booleans[3][1] = false;
                } else {
                    b31 = true;
                    b32 = false;
                    b33 = false;
                    b34 = false;
                    b35 = false;
                    b36 = false;
                    booleans[3][1] = true;
                    booleans[3][2] = false;
                    booleans[3][3] = false;
                    booleans[3][4] = false;
                    booleans[3][5] = false;
                    booleans[3][6] = false;
                    button.setBackground(color);
                    btn32.setBackground(null);
                    btn33.setBackground(null);
                    btn34.setBackground(null);
                    btn35.setBackground(null);
                    btn36.setBackground(null);
                }
            }
        };
        btn31.addActionListener(btn31_action);
        ActionListener btn32_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getBackground().equals(color)) {
                    if (!b31 && !b33 && !b34 && !b35 && !b36)
                        return;
                    button.setBackground(null);
                    b32 = false;
                    booleans[3][2] = false;
                } else {
                    b31 = false;
                    b32 = true;
                    b33 = false;
                    b34 = false;
                    b35 = false;
                    b36 = false;
                    booleans[3][1] = false;
                    booleans[3][2] = true;
                    booleans[3][3] = false;
                    booleans[3][4] = false;
                    booleans[3][5] = false;
                    booleans[3][6] = false;
                    btn31.setBackground(null);
                    button.setBackground(color);
                    btn33.setBackground(null);
                    btn34.setBackground(null);
                    btn35.setBackground(null);
                    btn36.setBackground(null);
                }
            }
        };
        btn32.addActionListener(btn32_action);
        ActionListener btn33_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getBackground().equals(color)) {
                    if (!b31 && !b32 && !b34 && !b35 && !b36)
                        return;
                    button.setBackground(null);
                    b33 = false;
                    booleans[3][3] = false;
                } else {
                    b31 = false;
                    b32 = false;
                    b33 = true;
                    b34 = false;
                    b35 = false;
                    b36 = false;
                    booleans[3][1] = false;
                    booleans[3][2] = false;
                    booleans[3][3] = true;
                    booleans[3][4] = false;
                    booleans[3][5] = false;
                    booleans[3][6] = false;
                    btn31.setBackground(null);
                    btn32.setBackground(null);
                    button.setBackground(color);
                    btn34.setBackground(null);
                    btn35.setBackground(null);
                    btn36.setBackground(null);
                }
            }
        };
        btn33.addActionListener(btn33_action);
        ActionListener btn34_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getBackground().equals(color)) {
                    if (!b31 && !b32 && !b33 && !b35 && !b36)
                        return;
                    button.setBackground(null);
                    b34 = false;
                    booleans[3][4] = false;
                } else {
                    b31 = false;
                    b32 = false;
                    b33 = false;
                    b34 = true;
                    b35 = false;
                    b36 = false;
                    booleans[3][1] = false;
                    booleans[3][2] = false;
                    booleans[3][3] = false;
                    booleans[3][4] = true;
                    booleans[3][5] = false;
                    booleans[3][6] = false;
                    btn31.setBackground(null);
                    btn32.setBackground(null);
                    btn33.setBackground(null);
                    button.setBackground(color);
                    btn35.setBackground(null);
                    btn36.setBackground(null);
                }
            }
        };
        btn34.addActionListener(btn34_action);
        ActionListener btn35_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getBackground().equals(color)) {
                    if (!b31 && !b32 && !b33 && !b34 && !b36)
                        return;
                    button.setBackground(null);
                    b35 = false;
                    booleans[3][5] = false;
                } else {
                    b31 = false;
                    b32 = false;
                    b33 = false;
                    b34 = false;
                    b35 = true;
                    b36 = false;
                    booleans[3][1] = false;
                    booleans[3][2] = false;
                    booleans[3][3] = false;
                    booleans[3][4] = false;
                    booleans[3][5] = true;
                    booleans[3][6] = false;
                    btn31.setBackground(null);
                    btn32.setBackground(null);
                    btn33.setBackground(null);
                    btn34.setBackground(null);
                    button.setBackground(color);
                    btn36.setBackground(null);
                }
            }
        };
        btn35.addActionListener(btn35_action);
        ActionListener btn36_action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                if (button.getBackground().equals(color)) {
                    if (!b31 && !b32 && !b33 && !b34 && !b35)
                        return;
                    button.setBackground(null);
                    b36 = false;
                    booleans[3][6] = false;
                } else {
                    b31 = false;
                    b32 = false;
                    b33 = false;
                    b34 = false;
                    b35 = false;
                    b36 = true;
                    booleans[3][1] = false;
                    booleans[3][2] = false;
                    booleans[3][3] = false;
                    booleans[3][4] = false;
                    booleans[3][5] = false;
                    booleans[3][6] = true;
                    btn31.setBackground(null);
                    btn32.setBackground(null);
                    btn33.setBackground(null);
                    btn34.setBackground(null);
                    btn35.setBackground(null);
                    button.setBackground(color);
                }
            }
        };
        btn36.addActionListener(btn36_action);


        //인터페이스 구현
        ActionListener btn41_action = new ActionListener() {
            @Override // 어노테이션(Annotation)
            public void actionPerformed(ActionEvent e) {

                //어떤 컴포넌트에 이벤트가 발생하였는지 이벤트 소스를 알려줌
                //get.Source는 오브젝트 타입을 반환함
                //형변환을 통하여 다양하게 사용할수 있음
                JButton button = (JButton) e.getSource();

                QueryStatement querystatement = new QueryStatement();
                ArrayList<Output> outputs = querystatement.Output(con, querystatement.Query(booleans));


                Integer[] id = new Integer[5];
                String[] name = new String[5];
                String[] category = new String[5];
                Float[] grade = new Float[5];
                Double[] distance = new Double[5];

                int i = 0;
                for (Output output : outputs) {
                    id[i] = output.getRestaurantID();
                    name[i] = output.getRestaurantName();
                    category[i] = output.getRestaurantCategory();
                    grade[i] = output.getAgvGrade();
                    distance[i] = output.getDistance();
                    i++;
                }

                txtnull.setText("");
                if (id[0] == null) {
                    txtnull.setText("조건에 맞는 식당이 없습니다.");
                    txt1.setText("");
                    txt2.setText("");
                    txt3.setText("");
                    txt4.setText("");
                    txt5.setText("");
                } else {
                    txt1.setText(name[0] + " " + category[0] + " 평점" + grade[0] + " 거리" + distance[0] + "km");
                    if (id[1] == null)
                        txt2.setText("");
                    else
                        txt2.setText(name[1] + " " + category[1] + " 평점" + grade[1] + " 거리" + distance[1] + "km");
                    if (id[2] == null)
                        txt3.setText("");
                    else
                        txt3.setText(name[2] + " " + category[2] + " 평점" + grade[2] + " 거리" + distance[2] + "km");
                    if (id[3] == null)
                        txt4.setText("");
                    else
                        txt4.setText(name[3] + " " + category[3] + " 평점" + grade[3] + " 거리" + distance[3] + "km");
                    if (id[4] == null)
                        txt5.setText("");
                    else
                        txt5.setText(name[4] + " " + category[4] + " 평점" + grade[4] + " 거리" + distance[4] + "km");
                }

                button.setText("다시 하기");
                // button.setBackground(new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));

            }
        };
        //구현화한 클래스의 객체를 집어넣음
        btn41.addActionListener(btn41_action);


        //부모 프레임에다가 자식 컴포넌트 추가
        frm.getContentPane().add(txt1);
        frm.getContentPane().add(txt2);
        frm.getContentPane().add(txt3);
        frm.getContentPane().add(txt4);
        frm.getContentPane().add(txt5);
        frm.getContentPane().add(txtnull);

        frm.getContentPane().add(btn00);
        frm.getContentPane().add(btn11);
        frm.getContentPane().add(btn12);
        frm.getContentPane().add(btn13);
        frm.getContentPane().add(btn14);
        frm.getContentPane().add(btn15);
        frm.getContentPane().add(btn16);
        frm.getContentPane().add(btn21);
        frm.getContentPane().add(btn22);
        frm.getContentPane().add(btn23);
        frm.getContentPane().add(btn24);
        frm.getContentPane().add(btn25);
        frm.getContentPane().add(btn26);
        frm.getContentPane().add(btn27);
        frm.getContentPane().add(btn28);
        frm.add(btn31);
        frm.add(btn32);
        frm.add(btn33);
        frm.add(btn34);
        frm.add(btn35);
        frm.add(btn36);
        frm.getContentPane().add(btn31);
        frm.getContentPane().add(btn32);
        frm.getContentPane().add(btn33);
        frm.getContentPane().add(btn34);
        frm.getContentPane().add(btn35);
        frm.getContentPane().add(btn36);
        frm.getContentPane().add(btn41);



        frm.setVisible(true); //부모 프레임이 보임
    }
}