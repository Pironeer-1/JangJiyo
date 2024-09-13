package org.example;

import Output.Printer;
import Skills.Skills;
import Character.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private int statusPoint = 13;
    private List<Player> playerList = new ArrayList<>();
    private Enemy enemy;
    private Printer printer = new Printer();

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayers() {
        Scanner sc = new Scanner(System.in);
        int listNum;
        while (true) {
            try {
                printer.printMessage("플레이어 인원을 정하세요: ");
                listNum = sc.nextInt();
                if (listNum <= 0) {
                    printer.printMessage("플레이어 인원은 1 이상이어야 합니다.");
                    continue;
                }
                break;
            } catch (Exception e) {
                printer.printMessage("잘못된 입력입니다. 숫자를 입력해주세요.");
            }
        }
        for (int i = 0; i < listNum; i++) {
            Player player = new Player();
            player.setStatus(statusPoint);
            playerList.add(player);
        }
    }

    public void setEnemy() {
        enemy = new Enemy(playerList.size());
    }

    public boolean turnCheck() {
        playerList.removeIf(player -> player.getHp() <= 0);
        if (playerList.isEmpty() || enemy.getHp() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public Player selectTargetPlayer() {
        Random random = new Random();
        return playerList.get(random.nextInt(playerList.size()));
    }

    public void game() {
        setPlayers();
        setEnemy();

        while (turnCheck()) {
            for (int playerIndex = 0; playerIndex < playerList.size(); playerIndex++) {
                Player player = playerList.get(playerIndex);
                player.attack(enemy, playerIndex);
                if (enemy.getHp() == 0) {
                    break;
                }
            }

            if (turnCheck()) {
                Player targetPlayer = selectTargetPlayer();
                int targetIndex = playerList.indexOf(targetPlayer);

                enemy.attack(targetPlayer, targetIndex);
            } else {
                break;
            }
        }

        if (enemy.getHp() <= 0) {
            printer.printMessage("축하합니다! 승리하셨습니다!");
        } else {
            printer.printMessage("아쉽지만 패배하셨습니다.");
        }
    }

    public void executeSkill(CharacterType attacker, CharacterType target, Skills skill) {
        skill.executeSkill(attacker, target);
    }
    public void executeSkill(CharacterType target, Skills skill) {
        skill.executeSkill(target);
    }
}
