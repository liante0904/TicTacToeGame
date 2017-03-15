package com.example.shinsolar.tictactoegame_project;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by shinsolar on 2016-07-22.
 */
public class Tic_Tac_Toe_Game extends Activity implements View.OnClickListener {

    AlertDialog.Builder builder;
    TextView textView;
    int turn;
    int[][] btn = {{R.id.game_1bt, R.id.game_2bt, R.id.game_3bt},
            {R.id.game_4bt, R.id.game_5bt, R.id.game_6bt},
            {R.id.game_7bt, R.id.game_8bt, R.id.game_9bt},};
    Button[][] btns = new Button[btn.length][btn[0].length];

    int player_flag = 1; // 공격자를 판별하는 변수
    int index_x = 10, index_y = 10;
    int winner = 0;
    int[][] map = {{3, 3, 3},
            {3, 3, 3},
            {3, 3, 3}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe_game);

        builder = new AlertDialog.Builder(this);
        textView = (TextView) findViewById(R.id.current);

        for (int i = 0; i < btn.length; i++) {
            for (int ii = 0; ii < btn[0].length; ii++) {
                btns[i][ii] = (Button) findViewById(btn[i][ii]);
                btns[i][ii].setOnClickListener(this);
                btns[i][ii].setBackgroundColor(Color.GRAY);
            }
        }
        //     Toast.makeText(getApplicationContext(), String.valueOf(map[0][0] + ", " + map[0][1] + ", " + map[0][2] + ", \n" + map[1][0] + ", " + map[1][1] + ", " + map[1][2] + ", \n" + map[2][0] + ", " + map[2][1] + ", " + map[2][2] + ", "), Toast.LENGTH_SHORT).show();
        //   Toast.makeText(getApplicationContext(), "선공격자 " + String.valueOf(player_flag) + "입니다", Toast.LENGTH_SHORT).show();
        textView.setText("현재 공격자" + player_flag + "\n 내가 찍은 좌표  X:" + index_y + ",  " + "Y:" + index_y);

    }
    public void onClick_rule(View view){

        builder.create();
        builder.setTitle("게임 규칙입니다.");
        builder.setMessage("틱택토는 가로 세로 3줄의 빙고게임입니다. \n\n 1. 각자 플레이어가 숫자를 선택합니다. \n\n 2. 가로, 세로, 대각선, 1줄의 빙고를  \n\n 먼저 완성하면 승리 합니다. \n\n 즐거운 게임 되세요!! ");
        builder.setIcon(R.drawable.android_icon);
        builder.setPositiveButton("게임하러 가기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "게임이 시작되었습니다.", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    public void init(View view){

        Toast.makeText(getApplicationContext(), "게임이 초기화 되었습니다.",Toast.LENGTH_SHORT).show();
        for (int i = 0; i < btn.length; i++) {
            for (int ii = 0; ii < btn[0].length; ii++) {
                map[i][ii] = 3;
                btns[i][ii].setBackgroundColor(Color.GRAY);
                btns[i][ii].setText("Area");
                index_x = 10; index_y = 10;
                winner = 0;
            }

        }
        player_flag = 1;


    }

    @Override
    public void onClick(View v) {
        int chk = 0;
        if (winner == 0) { // 승자 판별, 승자가 발생하면 게임을 멈추고 초기화를 기다림


            if (player_flag == 1 || player_flag == 2) { //정상적인 플레이어 판별
                for (int i = 0; i < btn.length; i++) {
                    for (int ii = 0; ii < btn[0].length; ii++) {
                        if (v.getId() == btn[i][ii]) {

                            index_x = i;
                            index_y = ii;
                            break;
                        }
                    }
                }
                //   Toast.makeText(getApplicationContext(), "area :" + btns[index_x][index_y].getText().toString() + "\n : X = " + index_x + ",  " + "Y = " + index_y, Toast.LENGTH_SHORT).show();
                chk = map[index_x][index_y];
                if (player_flag == 1) {
                    if (chk == 3) {
                        map[index_x][index_y] = player_flag;
                        //    Toast.makeText(getApplicationContext(), "공격 되었습니다.", Toast.LENGTH_SHORT).show();
                        //     Toast.makeText(getApplicationContext(), String.valueOf(map[0][0] + ", " + map[0][1] + ", " + map[0][2] + ", \n" + map[1][0] + ", " + map[1][1] + ", " + map[1][2] + ", \n" + map[2][0] + ", " + map[2][1] + ", " + map[2][2] + ", "), Toast.LENGTH_SHORT).show();
                        textView.setText("현재 공격자" + player_flag + "\n 적군이  찍은 좌표  X:" + index_y + ",  " + "Y:" + index_y);
                        btns[index_x][index_y].setTag(player_flag);
                        btns[index_x][index_y].setBackgroundResource(R.drawable.player1);
                        btns[index_x][index_y].setText("Player"+player_flag );
                        btns[index_x][index_y].setTextSize(18F);
                        Toast.makeText(getApplicationContext(), String.valueOf((int)btns[index_x][index_y].getTag()), Toast.LENGTH_SHORT).show();
                        turn++;
                    } else if (chk == player_flag) {
                        //   Toast.makeText(getApplicationContext(), "이미 니가 공격 했음.", Toast.LENGTH_SHORT).show();
                        //    Toast.makeText(getApplicationContext(), String.valueOf(map[0][0] + ", " + map[0][1] + ", " + map[0][2] + ", \n" + map[1][0] + ", " + map[1][1] + ", " + map[1][2] + ", \n" + map[2][0] + ", " + map[2][1] + ", " + map[2][2] + ", "), Toast.LENGTH_SHORT).show();
                    } else if (chk == 2) {
                        //   Toast.makeText(getApplicationContext(), "적군이 공격 했음.", Toast.LENGTH_SHORT).show();
                        //    Toast.makeText(getApplicationContext(), String.valueOf(map[0][0] + ", " + map[0][1] + ", " + map[0][2] + ", \n" + map[1][0] + ", " + map[1][1] + ", " + map[1][2] + ", \n" + map[2][0] + ", " + map[2][1] + ", " + map[2][2] + ", "), Toast.LENGTH_SHORT).show();
                    }
                } else if (player_flag == 2) { // 플레이어가 2일때
                    if (chk == 3) {
                        map[index_x][index_y] = player_flag;
                        //   Toast.makeText(getApplicationContext(), "공격 되었습니다.", Toast.LENGTH_SHORT).show();
                        //   Toast.makeText(getApplicationContext(), String.valueOf(map[0][0] + ", " + map[0][1] + ", " + map[0][2] + ", \n" + map[1][0] + ", " + map[1][1] + ", " + map[1][2] + ", \n" + map[2][0] + ", " + map[2][1] + ", " + map[2][2] + ", "), Toast.LENGTH_SHORT).show();
                        textView.setText("현재 공격자" + player_flag + "\n 적군이  찍은 좌표  X:" + index_y + ",  " + "Y:" + index_y);
                        btns[index_x][index_y].setBackgroundResource(R.drawable.player2);
                        btns[index_x][index_y].setText("Player"+player_flag );
                        btns[index_x][index_y].setTextSize(18F);
                        turn++;
                    } else if (chk == player_flag) {
                        //   Toast.makeText(getApplicationContext(), "이미 니가 공격 했음.", Toast.LENGTH_SHORT).show();
                        //   Toast.makeText(getApplicationContext(), String.valueOf(map[0][0] + ", " + map[0][1] + ", " + map[0][2] + ", \n" + map[1][0] + ", " + map[1][1] + ", " + map[1][2] + ", \n" + map[2][0] + ", " + map[2][1] + ", " + map[2][2] + ", "), Toast.LENGTH_SHORT).show();
                    } else if (chk == 2) {
                        //   Toast.makeText(getApplicationContext(), "적군이 공격 했음.", Toast.LENGTH_SHORT).show();
                        //   Toast.makeText(getApplicationContext(), String.valueOf(map[0][0] + ", " + map[0][1] + ", " + map[0][2] + ", \n" + map[1][0] + ", " + map[1][1] + ", " + map[1][2] + ", \n" + map[2][0] + ", " + map[2][1] + ", " + map[2][2] + ", "), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            // 승부 판별 구간
            if (map[1][1] != 3) { //중앙선점시 승리 6가지 판별
                chk = map[1][1];
                if (map[0][0] == chk && map[1][1] == chk && map[2][2] == chk ||
                        map[0][2] == chk && map[1][1] == chk && map[2][0] == chk ||
                        map[1][1] == chk && map[1][0] == chk && map[1][2] == chk ||
                        map[0][1] == chk && map[1][1] == chk && map[2][1] == chk) {
                    //  Toast.makeText(getApplicationContext(), "player" + player_flag + "승리!", Toast.LENGTH_SHORT).show();
                    winner = player_flag;
                    player_flag = 0;
                    textView.setText("게임의 승리자는" + winner + "입니다. \n" + "새로운 게임을 눌러 새로 시작해주세요.");
                    //  Toast.makeText(getApplicationContext(), String.valueOf(map[0][0] + ", " + map[0][1] + ", " + map[0][2] + ", \n" + map[1][0] + ", " + map[1][1] + ", " + map[1][2] + ", \n" + map[2][0] + ", " + map[2][1] + ", " + map[2][2] + ", "), Toast.LENGTH_SHORT).show();
                }
            }
            int chk1 = 1;
            int chk22 = map[2][2];

            if (map[0][0] == player_flag && map[0][1] == player_flag&& map[0][2] ==player_flag ||
                    map[0][0] == player_flag && map[1][0] == player_flag && map[2][0] == player_flag ||
                    map[2][0] == player_flag && map[2][1] == player_flag && map[2][2] == player_flag ||
                    map[0][2] == player_flag && map[1][2] == player_flag && map[2][2] == player_flag){  //그외 승리조건 4가지

                winner = player_flag;
                textView.setText("게임의 승리자는" + winner + "입니다. \n" + "새로운 게임을 눌러 새로 시작해주세요.1구간");
                //       Toast.makeText(getApplicationContext(), String.valueOf(map[0][0] + ", " + map[0][1] + ", " + map[0][2] + ", \n" + map[1][0] + ", " + map[1][1] + ", " + map[1][2] + ", \n" + map[2][0] + ", " + map[2][1] + ", " + map[2][2] + ", "), Toast.LENGTH_SHORT).show();
            }






            //승자가 나오지 않을 경우 player_flag 전환
            if (player_flag == 1) {
                player_flag = 2;
            } else if (player_flag == 2) {
                player_flag = 1;
            }


            int[][] draw = new int[btn.length][btn[0].length];
            for (int i = 0; i < btn.length; i++) {
                for (int ii = 0; ii < btn[0].length; ii++) {
                    draw[i][ii] = map[i][ii];
                }
            }

            int x =0; int y = 0;

            while (x ==3){
                while (y ==3){
                    if (draw[x][y] == 3){
                        textView.setText("이번 게임은 무승부입니다.. \n" + "새로운 게임을 눌러 새로 시작해주세요.");
                    }
                }
                x++;
                y++;
            }
            // 공격한 버튼 음영처리 구간

 /*           if (Integer.parseInt(btns[index_x][index_y].getTag().toString()) == 1){
                btns[index_x][index_y].setBackgroundResource(R.drawable.player1);
            }
            if (Integer.parseInt(btns[index_x][index_y].getTag().toString()) == 2){
                btns[index_x][index_y].setBackgroundResource(R.drawable.player2);
            }
*/
        } else {
            //  Toast.makeText(getApplicationContext(), "게임의 승리자는" + winner + "입니다. \n" + "새로운 게임을 눌러 새로 시작해주세요.", Toast.LENGTH_SHORT).show();
            textView.setText("게임의 승리자는" + winner + "입니다. \n" + "새로운 게임을 눌러 새로 시작해주세요.");
            //  Toast.makeText(getApplicationContext(), String.valueOf(map[0][0] + ", " + map[0][1] + ", " + map[0][2] + ", \n" + map[1][0] + ", " + map[1][1] + ", " + map[1][2] + ", \n" + map[2][0] + ", " + map[2][1] + ", " + map[2][2] + ", "), Toast.LENGTH_SHORT).show();
            turn = 0;
        }
    }
}









