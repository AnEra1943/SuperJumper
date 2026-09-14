package com.anlai.superjumper;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;//图片
import com.badlogic.gdx.graphics.g2d.SpriteBatch;//高效把图片画到屏幕上
import com.badlogic.gdx.utils.ScreenUtils;//屏幕相关工具
import com.badlogic.gdx.Gdx; //delta
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;//游戏世界映射到实际窗口
import com.badlogic.gdx.graphics.g2d.BitmapFont; //字体

public class GameScreen implements Screen {
    private KeyBoardController KeyBoardController;
    private SuperJumper game;
    public GameScreen(SuperJumper game){
        this.game = game;

    }
    private SpriteBatch batch;
    private Texture items;

    private TextureRegion player;
    private TextureRegion playerright;
    private TextureRegion playerleft;
    private TextureRegion playerdead;
    private TextureRegion playerleftspace;

    private TextureRegion playerrightspace;

    private TextureRegion[] gold = new TextureRegion[3];
    private TextureRegion[] staticplatform = new TextureRegion[4];
    private int goldFrame;
    private float goldTimer;
    private int goldCount = 20;
    private float[] goldX = new float[20];
    private float[] goldY = new float[20];
    private int[] goldPlatform = new int[20];


    private boolean[] goldCollected = new boolean[20];
    private int score = 0;
    private float gameTime = 0;

    private TextureRegion Platform;
    float[] PlatformX = new float[100];
    private float[] PlatformY = new float[100];
    private boolean[] PlatformMoveLeft = new boolean[100];
    private boolean[] PlatformStatic = new boolean[100];
    private boolean[] PlatformSpring = new boolean[100];
    // 平台是否正在碎裂
    private boolean[] PlatformBreaking = new boolean[100];
    private boolean[] PlatformDestroyed = new boolean[100];

    // 碎裂动画当前帧
    private int[] PlatformBreakFrame = new int[100];

    // 每个平台自己的碎裂计时器
    private float[] PlatformBreakTimer = new float[100];


    private float PlatformSpeed = 100;


    private boolean OnPlatform = false;
    private int currentPlatform = -1;



    private Texture background;

    private TextureRegion castle;
    private TextureRegion princess;
    private TextureRegion spring;
    //怪兽
    private TextureRegion Monster1;
    private TextureRegion Monster2;
    private TextureRegion Monster1Left;
    private TextureRegion Monster2Left;
    private TextureRegion Monster;

    private float[] MonsterX = new float[8];
    private float[] MonsterY = new float[8];

    private TextureRegion MonsterDraw;

    private boolean[] MonsterMoveLeft = new boolean[8];

    private float MonsterSpeed = 120;
    //怪兽动画
    private int MonsterFrame = 0;
    private float MonsterTimer = 0;
    private int MonsterCount = 8;



    private float castleX = 80;
    private float castleY = 6070;

    private float princessX = 86;
    private float princessY = 6090;






    private TextureRegion background_final;
    private Viewport viewport;
    private float cameraY = 0;
    private boolean cameraFollow = false;



    private float x = 0;
    private float y = 0;
    private float oldY = 0;//记录人物的上一帧

    private float speed = 0;
    private float velocityY = 0;

    private BitmapFont font;
    //游戏失败判定
    private boolean gameOver = false;

    // 开始掉落时的高度
    private float fallStartY = 0;
    private boolean isFalling = false;

    // 最大允许掉落距离
    private float maxFallDistance = 200;
    private boolean playerDead = false;
    private float deathTimer = 0;
    private float restartTimer = 0;

    private boolean playerWin = false;
    private float winTimer = 0;
    private float winRestartTimer = 0;
    private boolean savedScore = false;

    private void restartGame() {

        // =========================
        // 玩家状态重新开始
        // =========================
        gameOver = false;
        playerDead = false;
        savedScore = false;

        playerWin = false;
        winTimer = 0;
        winRestartTimer = 0;

        deathTimer = 0;
        restartTimer = 0;

        isFalling = false;
        fallStartY = 0;

        x = 50;
        y = 50;

        //测试胜利位置
//        x = princessX ;
//        y = princessY + 50;

        velocityY = 0;
        speed = 0;

        player = playerright;

        OnPlatform = false;
        currentPlatform = -1;

        cameraY = 0;
        cameraFollow = false;


        // =========================
        // 分数时间重新开始
        // =========================
        score = 0;
        gameTime = 0;


        // =========================
        // 重新生成100个平台
        // =========================
        for (int i = 0; i < 100; i++){

            PlatformX[i] = 80 + (float)Math.random() * 93;
            PlatformY[i] = 60 + i * 60;

            PlatformStatic[i] = false;
            PlatformSpring[i] = false;
            PlatformBreaking[i] = false;
            PlatformDestroyed[i] = false;
            PlatformBreakFrame[i] = 0;
            PlatformBreakTimer[i] = 0;

            if (i == 0) {
                PlatformStatic[i] = Math.random() < 0.3;
            } else {
                if (PlatformStatic[i - 1]) {
                    PlatformStatic[i] = false;
                } else {
                    PlatformStatic[i] = Math.random() < 0.3;
                }
            }

            PlatformMoveLeft[i] = false;
            if (!PlatformStatic[i]) {
                PlatformMoveLeft[i] = Math.random() < 0.5;
            }
        }
        // =========================
        // 生成怪兽
        // 每一行最多出现一个怪兽
        // =========================

        // 记录哪些平台行已经生成过怪兽
        boolean[] monsterLayerUsed = new boolean[100];
        for (int i = 0; i < MonsterCount; i++) {
            int layer;

            // 不断随机，直到找到没有使用过的行
            do {
                layer = (int)(Math.random() * 95);
            } while (monsterLayerUsed[layer]);

            // 记录这一行已经被使用
            monsterLayerUsed[layer] = true;

            // 怪兽的 X 坐标随机
            MonsterX[i] = (float)Math.random() * 300;

            // 怪兽的 Y 坐标跟对应平台保持一致
            MonsterY[i] = PlatformY[layer] + 20;

            // 随机移动方向
            MonsterMoveLeft[i] = Math.random() < 0.5;
        }

        // =========================
        // 金币重新开始
        // =========================

        // 所有金币恢复成“没有吃掉”
        for (int i = 0; i < goldCount; i++) {
            goldCollected[i] = false;
        }


        // 重新随机20个金币对应的平台
        for (int i = 0; i < goldCount; i++) {

            int randomPlatform;

            do {

                randomPlatform = (int)(Math.random() * 95);

                // 检查这个平台是不是已经有金币
                boolean used = false;

                for (int j = 0; j < i; j++) {

                    if (goldPlatform[j] == randomPlatform) {
                        used = true;
                        break;
                    }
                }

                if (!used) {
                    goldPlatform[i] = randomPlatform;
                    break;
                }

            } while (true);
        }


        // =========================
        // 把金币对应的平台顺序重新打乱
        // =========================
        for (int i = goldCount - 1; i > 0; i--) {

            int random = (int)(Math.random() * (i + 1));

            int temp = goldPlatform[i];

            goldPlatform[i] = goldPlatform[random];

            goldPlatform[random] = temp;
        }


        // =========================
        // 根据新的平台重新生成金币位置
        // =========================
        for (int i = 0; i < goldCount; i++) {

            // 金币 X 重新随机
            goldX[i] = (float)Math.random() * 300;

            // 金币 Y 跟着新的平台
            goldY[i] = PlatformY[goldPlatform[i]] + 20;
        }


        // 金币动画重新开始
        goldFrame = 0;
        goldTimer = 0;
        // =========================
        // 先把100个平台的弹簧全部清空
        // =========================
        for (int i = 0; i < 100; i++) {
            PlatformSpring[i] = false;
        }
        // =========================
        // 重新随机生成5个弹簧
        // 两个弹簧之间至少间隔5个平台
        // =========================
        int springCount = 0;
        while (springCount < 5) {

            int randomPlatform = (int)(Math.random() * 95);

            boolean tooClose = false;

            for (int j = 0; j < 100; j++) {
                if (PlatformSpring[j]) {
                    if (Math.abs(randomPlatform - j) <= 5) {
                        tooClose = true;
                        break;
                    }
                }
            }

            if (!tooClose) {
                PlatformSpring[randomPlatform] = true;
                springCount++;
            }
        }
    }

    @Override
    public void show() {
        KeyBoardController = new KeyBoardController();//注册鼠标监听器器
        Gdx.input.setInputProcessor(KeyBoardController);
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font.fnt"));
        items = new Texture("items.png");
        Platform = new TextureRegion(items, 64, 161, 67, 14);
        playerleft = new TextureRegion(items, 97, 122, 33, 38);
        playerright = new TextureRegion(items, 65, 122, 33, 38);
        playerleftspace = new TextureRegion(items, 0, 122, 33, 38);
        playerrightspace = new TextureRegion(items, 35, 122, 33, 38);
        playerdead  = new TextureRegion(items,132,126 ,33,38);
        castle = new TextureRegion(items,131,68,61,58);
        princess = new TextureRegion(items,167,122,31,38);
        spring = new TextureRegion(items,132,5,26,21);
        Monster1 = new TextureRegion(items,0,160,32,31);
        Monster2 = new TextureRegion(items,33,160,32,31);
        Monster1Left = new TextureRegion(items,0,160,32,31);
        Monster2Left = new TextureRegion(items,33,160,32,31);
        Monster1Left.flip(true,false);
        Monster2Left.flip(true,false);
        Monster = Monster1;
        player = playerright;
        playerleft.flip(true, false);//镜像反转
        playerleftspace.flip(true, false);
        background = new Texture("background.png");
        background_final = new TextureRegion(background,0,0,320,480);//截取图片有多大
        gold[0] = new TextureRegion(items,135,33,19,33);
        gold[1] = new TextureRegion(items,169,34,19,33);
        gold[2] = new TextureRegion(items,203,36,19,33);
        staticplatform[0] = new TextureRegion(items,64,161,67,14);
        staticplatform[1] = new TextureRegion(items, 64, 175, 67, 14);
        staticplatform[2] = new TextureRegion(items, 64, 191, 67, 14);
        staticplatform[3] = new TextureRegion(items, 64, 206, 67, 14);
        viewport = new FillViewport(320, 480);
        restartGame();

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        if (!playerDead && !playerWin) {
            gameTime += delta;
        }
        // 平台碎裂动画
        for (int i = 0; i < 100; i++) {

            if (PlatformBreaking[i]) {

                PlatformBreakTimer[i] += delta;

                // 每0.1秒播放下一帧
                if (PlatformBreakTimer[i] >= 0.1f) {

                    PlatformBreakTimer[i] = 0;
                    if (PlatformBreakFrame[i] < 3) {
                        PlatformBreakFrame[i]++;
                    }else {
                        PlatformBreaking[i] = false;
                        PlatformDestroyed[i] = true;
                    }

                }
            }
        }
        //金币播放动画
        goldTimer += delta;
        if (goldTimer >= 0.1f) {
            goldTimer = 0;
            goldFrame++;

            if (goldFrame >= 3) {
                goldFrame = 0;
            }
        }
        //怪兽动画
        MonsterTimer += delta;
        // =========================
        // 怪兽水平移动
        // =========================
        for(int i = 0; i < MonsterCount; i++){
            if(MonsterMoveLeft[i]){
                MonsterX[i] -= MonsterSpeed * delta;
            }else{
                MonsterX[i] += MonsterSpeed * delta;
            }
            //循环出现
            if(MonsterX[i] > 320){
                MonsterX[i] -= 320;
            }
            if(MonsterX[i] < 0){
                MonsterX[i] += 320;
            }
        }
        if(MonsterTimer > 0.2f){
            MonsterTimer = 0;
            MonsterFrame++;
            if(MonsterFrame >= 2){
                MonsterFrame = 0;
            }
            if(MonsterFrame == 0){
                Monster = Monster1;
            }
            else{
                Monster = Monster2;
            }
        }
        for (int i = 0; i < 100; i++) {

            if(!PlatformStatic[i]) {
                if (PlatformMoveLeft[i]) {
                    PlatformX[i] -= PlatformSpeed * delta;
                } else {
                    PlatformX[i] += PlatformSpeed * delta;
                }

                if (PlatformX[i] > 320) {
                    PlatformX[i] -= 320;
                }

                if (PlatformX[i] < 0) {
                    PlatformX[i] += 320;
                }
            }
        }
        x += speed * delta;
        if (x > 320) {
            x -= 320;
            System.out.println(x);
        }
        if (x < 0) {
            x += 320;
        }
        if(!OnPlatform) {
            velocityY -= 500 * delta;
            oldY = y;
            y += velocityY * delta;
        }
        //测试
//        if (OnPlatform) {
//            System.out.println(
//                "站平台: x=" + x +
//                    " y=" + y +
//                    " velocityY=" + velocityY +
//                    " PlatformX=" + PlatformX[currentPlatform]
//            );
//        }

        // 开始向下掉落
        if (velocityY < 0 && !OnPlatform && !playerDead && !isFalling) {
            isFalling = true;
            fallStartY = y;
        }

        // 正在掉落，检查距离
        if (isFalling && !playerDead && !playerWin) {
            if (fallStartY - y > maxFallDistance) {
                playerDead = true;
                speed = 0;
                player = playerdead;
                deathTimer = 0;
            }
        }


        if (y < 0) {
            y = 0;
            velocityY = 0;
            OnPlatform = false;
            isFalling = false;
            if (playerDead == false) {
                if (player == playerrightspace) {
                    player = playerright;
                } else if (player == playerleftspace) {
                    player = playerleft;
                }
            }
        }
        if (playerDead) {
            deathTimer += delta;

            if (deathTimer >= 2) {
                gameOver = true;
            }
            if (gameOver) {
                restartTimer += delta;
            }
        }
        // 胜利计时
        if (playerWin) {
            if(!savedScore){
                game.saveManager.checkScore(score, (int)gameTime);
                savedScore = true;
            }

            // 玩家保持静止
            x = princessX + 20;
            y = princessY;
            speed = 0;
            velocityY = 0;
            player = playerleft;

            winTimer += delta;

            // 第一个2秒结束
            if (winTimer >= 2) {
                winRestartTimer += delta;
            }
        }

        // 碰撞检测
        // 检查人物是否落到 50 个平台中的某一个
        for (int i = 0; i < 100; i++) {
            // 正常检测平台
            if (!PlatformDestroyed[i] && oldY >= PlatformY[i] + 14 && y < PlatformY[i] + 14 &&
                x  > PlatformX[i] - 20 && x < PlatformX[i] + 55 && !PlatformBreaking[i]) {

                y = PlatformY[i] + 14;

                // 如果这个平台有弹簧
                if (PlatformSpring[i]) {

                    // 弹簧使用一次后消失
                    PlatformSpring[i] = false;

                    // 平台开始碎裂
                    PlatformBreaking[i] = true;
                    PlatformBreakFrame[i] = 0;
                    PlatformBreakTimer[i] = 0;

                    // 如果是移动平台，立刻停止
                    PlatformMoveLeft[i] = false;

                    //弹跳切换为跳跃图片
                    if(player == playerleft){
                        player = playerleftspace;
                    }else if(player == playerright){
                        player = playerrightspace;
                    }

                    // 弹簧跳跃
                    OnPlatform = false;
                    velocityY = 700;

                } else {

                    // 普通平台
                    OnPlatform = true;
                    velocityY = 0;
                }

                cameraFollow = true;
                currentPlatform = i;
                isFalling = false;
                fallStartY = 0;
            }

            // 检查平台从右边超出屏幕后，
            // 在左边重新出现的那一份
            if (!PlatformDestroyed[i] && PlatformX[i] + 67 > 320) {

                float leftPlatformX = PlatformX[i] - 320;

                if (!PlatformBreaking[i] &&
                    oldY >= PlatformY[i] + 14 &&
                    y < PlatformY[i] + 14 &&
                    x > leftPlatformX - 20 &&
                    x < leftPlatformX + 60) {

                    y = PlatformY[i] + 14;

                    // 如果这个平台有弹簧
                    if (PlatformSpring[i]) {

                        // 弹簧使用一次后消失
                        PlatformSpring[i] = false;

                        // 平台开始碎裂
                        PlatformBreaking[i] = true;
                        PlatformBreakFrame[i] = 0;
                        PlatformBreakTimer[i] = 0;

                        // 如果是移动平台，立刻停止
                        PlatformMoveLeft[i] = false;

                        // 弹跳切换为跳跃图片
                        if (player == playerleft) {
                            player = playerleftspace;
                        } else if (player == playerright) {
                            player = playerrightspace;
                        }

                        // 弹簧跳跃
                        OnPlatform = false;
                        velocityY = 700;

                    } else {

                        // 普通平台
                        OnPlatform = true;
                        velocityY = 0;
                    }

                    currentPlatform = i;
                    isFalling = false;
                    fallStartY = 0;
                    cameraFollow = true;
                }
            }
        }

        // 金币碰撞检测
        for (int i = 0; i < goldCount; i++) {

            // 如果这个金币还没有被吃
            if (!goldCollected[i]) {

                // 检查人物和金币有没有碰到
                if (x + 33 > goldX[i] &&
                    x < goldX[i] + 19 &&
                    y + 38 > goldY[i] &&
                    y < goldY[i] + 33) {

                    // 吃掉金币
                    goldCollected[i] = true;

                    // 分数 +100
                    score += 100;
                }
            }
        }
        // =========================
        // 怪兽碰撞检测
        // =========================
        if (!playerDead && !playerWin) {
            for(int i = 0; i < MonsterCount; i++){
                if(x + 25 > MonsterX[i] + 4 && x + 8 < MonsterX[i] + 28 &&
                    y + 30 > MonsterY[i] + 5 && y + 8 < MonsterY[i] + 26) {
                    playerDead = true;
                    speed = 0;
                    velocityY = 0;
                    player = playerdead;
                    deathTimer = 0;
                    // 已经死亡，不需要继续检查其他怪兽
                    break;
                }
            }
        }
        // 胜利检测
        if (!playerDead && !playerWin &&
            velocityY < 0 &&
            oldY >= princessY &&
            y <= princessY &&
            x + 33 > castleX &&
            x < castleX + 61) {

            playerWin = true;

            winTimer = 0;
            winRestartTimer = 0;

            speed = 0;
            velocityY = 0;

            y = princessY;
        }
        // =========================
        // 检测玩家是否还站在平台上
        // =========================
        if (OnPlatform) {

            // 防止 currentPlatform 变成 -1
            if (currentPlatform < 0 || currentPlatform >= 100) {
                OnPlatform = false;
            }

            // 当前平台已经被摧毁
            else if (PlatformDestroyed[currentPlatform]) {
                OnPlatform = false;
                currentPlatform = -1;
            }

            else {

                // =========================
                // 玩家站在移动平台上
                // =========================
                if (!PlatformStatic[currentPlatform]) {

                    if (PlatformMoveLeft[currentPlatform]) {
                        x -= PlatformSpeed * delta;
                    } else {
                        x += PlatformSpeed * delta;
                    }

                    // 玩家跟着平台移动后，也要进行屏幕环绕
                    if (x > 320) {
                        x -= 320;
                    }

                    if (x < 0) {
                        x += 320;
                    }
                }

                // =========================
                // 玩家在平台上的图片
                // =========================
                if (!playerDead) {
                    if (player == playerrightspace) {
                        player = playerright;
                    } else if (player == playerleftspace) {
                        player = playerleft;
                    }
                }

                // =========================
                // 判断玩家有没有离开平台
                // =========================

                // 玩家身体中心点
                float playerCenter = x + 16.5f;

                // 平台中心点
                float platformCenter = PlatformX[currentPlatform] + 33.5f;

                // 计算两个中心点的距离
                float distance = Math.abs(playerCenter - platformCenter);

                // 屏幕环绕情况下，计算另一边的距离
                float wrapDistance = 320 - distance;

                // 取较小的距离
                float realDistance = Math.min(distance, wrapDistance);

                // 如果玩家中心离平台中心太远
                if (realDistance > 42) {
//                    System.out.println(
//                        "掉平台！" +
//                            " x=" + x +
//                            " PlatformX=" + PlatformX[currentPlatform] +
//                            " playerCenter=" + playerCenter +
//                            " platformCenter=" + platformCenter +
//                            " distance=" + distance +
//                            " realDistance=" + realDistance
//                    );//测试
                    OnPlatform = false;
                    currentPlatform = -1;
                }

            }
        }

        if (!playerDead && !playerWin) {
            if (KeyBoardController.isLeftPressed() && OnPlatform == false) {
                speed = -200;
                if (player == playerrightspace || player == playerleftspace) {
                    player = playerleftspace;
                } else {
                    player = playerleft;
                }
            } else if (KeyBoardController.isRightPressed() && OnPlatform == false) {
                speed = 200;
                if (player == playerrightspace || player == playerleftspace) {
                    player = playerrightspace;
                } else {
                    player = playerright;
                }
            } else if (KeyBoardController.isRightPressed() && OnPlatform == true
                && PlatformMoveLeft[currentPlatform]) {
                speed = 300;
                player = playerright;
            } else if (KeyBoardController.isRightPressed() && OnPlatform == true
                && PlatformMoveLeft[currentPlatform] == false) {
                speed = 200;
                player = playerright;
            } else if (KeyBoardController.isLeftPressed() && OnPlatform == true
                && PlatformMoveLeft[currentPlatform]) {
                player = playerleft;
                speed = -200;
            } else if (KeyBoardController.isLeftPressed() && OnPlatform == true
                && PlatformMoveLeft[currentPlatform] == false) {
                speed = -300;
                player = playerleft;
            } else {
                speed = 0;
            }


            // 跳跃
            if (!playerDead && KeyBoardController.isSpaceJustPressed() &&
                (y == 0 || OnPlatform )){
                if (player == playerright) {
                    player = playerrightspace;
                } else if (player == playerleft) {
                    player = playerleftspace;
                }
                OnPlatform = false;
                velocityY = 300;
            }
        }
           KeyBoardController.resetSpaceJustPressed();

        // 摄像机跟随
        if (cameraFollow) {
            float playerScreenY = y - cameraY;

            if (playerScreenY > 240) {
                cameraY += playerScreenY - 240;
            }
            // 人物往下，摄像机往下
            if (playerScreenY < 240 && cameraY > 0) {
                cameraY -= 240 - playerScreenY;
            }
        }

        // 按钮判定
        // 按钮判定
        if (gameOver) {
            if (Gdx.input.justTouched()) {
                float touchX = Gdx.input.getX();
                float touchY = 480 - Gdx.input.getY();
                System.out.println("X = " + touchX);
                System.out.println("Y = " + touchY);
                if (touchX >= 83 && touchX <= 235 &&
                    touchY >= 191 && touchY <= 217) {
                    System.out.println("点击了 Try again!");
                    restartGame();
                } else if (touchX >= 114 && touchX <= 192 &&
                        touchY >= 161 && touchY <= 184) {
                    System.out.println("点击了 Menu");
                    game.setScreen(new StartScreen(game));
                }
            }
        }

        if (playerWin && winRestartTimer >= 2) {

            if (Gdx.input.justTouched()) {

                float touchX = Gdx.input.getX();
                float touchY = 480 - Gdx.input.getY();

                System.out.println("X = " + touchX);
                System.out.println("Y = " + touchY);

                if (touchX >= 75 && touchX <= 237 &&
                    touchY >= 189 && touchY <= 213) {
                    System.out.println("点击了 Play again!");
                    restartGame();
                }else if (touchX >= 114 && touchX <= 192 &&
                    touchY >= 158 && touchY <= 184) {
                    System.out.println("点击了 Menu");
                    game.setScreen(new StartScreen(game));
                }
            }
        }

        batch.begin();
        batch.draw(background_final, 0, 0, 320, 480); //显示图片多大

        for (int i = 0; i < 100; i++) {

            // 已经摧毁的平台，整个循环直接跳过
            if (PlatformDestroyed[i]) {
                continue;
            }

            // =========================
            // 绘制平台
            // =========================

            if (!PlatformStatic[i] && !PlatformBreaking[i]) {
                batch.draw(
                    Platform,
                    PlatformX[i],
                    PlatformY[i] - cameraY
                );
            }

            if (PlatformStatic[i] && !PlatformBreaking[i]) {
                batch.draw(
                    staticplatform[0],
                    PlatformX[i],
                    PlatformY[i] - cameraY
                );
            }

            // 碎裂动画
            if (PlatformBreaking[i]) {
                batch.draw(
                    staticplatform[PlatformBreakFrame[i]],
                    PlatformX[i],
                    PlatformY[i] - cameraY
                );
            }


            // =========================
            // 平台超出右边后，从左边重新显示
            // =========================

            if (PlatformX[i] + 67 > 320) {

                if (!PlatformBreaking[i]) {

                    if (!PlatformStatic[i]) {
                        batch.draw(Platform, PlatformX[i] - 320, PlatformY[i] - cameraY);
                    } else {
                        batch.draw(staticplatform[0], PlatformX[i] - 320, PlatformY[i] - cameraY);
                    }

                } else {

                    batch.draw(staticplatform[PlatformBreakFrame[i]], PlatformX[i] - 320, PlatformY[i] - cameraY);
                }
            }


            // =========================
            // 弹簧
            // =========================

            if (PlatformSpring[i]) {

                batch.draw(spring, PlatformX[i] + (67 - 26) / 2f, PlatformY[i] + 14 - 21 + 15 - cameraY);

                if (PlatformX[i] + 67 > 320) {

                    batch.draw(spring, PlatformX[i] - 320 + (67 - 26) / 2f, PlatformY[i] + 14 - 21 + 15 - cameraY);
                }
            }
        }


        for(int i = 0; i < goldCount; i++){
            if(!goldCollected[i]) {
                batch.draw(gold[goldFrame], goldX[i], goldY[i] - cameraY);
            }
        }
        // =========================
        // 绘制怪兽
        // =========================
        for(int i = 0; i < MonsterCount; i++){
            MonsterDraw = new TextureRegion(Monster);
            if(MonsterMoveLeft[i]){
                // 左飞，临时镜像
                MonsterDraw.flip(true,false);
            }
            //绘制右飞怪兽
            batch.draw(MonsterDraw, MonsterX[i], MonsterY[i] - cameraY);
            batch.draw(MonsterDraw, MonsterX[i], MonsterY[i]-cameraY);
            //超过右边循环
            if(MonsterX[i] + 32 > 320){
                batch.draw(MonsterDraw, MonsterX[i]-320, MonsterY[i]-cameraY);
            }
        }
        font.draw(batch, "Score:" + score,  85, 30);
        font.draw(batch, "Time:" + (int)gameTime +"s",110,50);
        batch.draw(castle,castleX, castleY - cameraY);
        batch.draw(princess,princessX, princessY - cameraY);

        //绘制人物
        if (playerDead) {
            batch.draw(player, x, y - cameraY - 5);
            if (x + 33 > 320) {
                batch.draw(player, x - 320, y - cameraY - 5);
            }
        } else if (!playerDead) {
            batch.draw(player, x, y - cameraY);
            if(x + 33 > 320 ){
                batch.draw(player, x - 320, y - cameraY);
            }
        }
        if (gameOver) {
            font.draw(batch, "You Lose!", 90, 300);
            if(restartTimer > 1){
                font.draw(batch, "Score:"+score, 90, 270);
            }
            if(restartTimer > 1.5){
                font.draw(batch,"Time:"+ (int)gameTime +"s",90,240);
            }

            if( restartTimer > 2){
                font.draw(batch, "Try again", 90, 210);
            }
            if( restartTimer > 2.5){
                font.draw(batch, "Menu", 120, 180);
            }
        }
        if (playerWin){
            font.getData().setScale(1);
            if (winTimer >= 0.5) {
                font.draw(batch, "You Win!", 100, 300);
            }
            if (winTimer >= 1) {
                font.draw(batch, "Score:"+score, 100, 270);
            }
            if (winTimer >= 1.5) {
                font.draw(batch, "Time:"+ (int)gameTime +"s",100,240);
            }
            if (winTimer >= 2.5) {
                font.draw(batch, "Menu", 120, 180);
            }
            if( winRestartTimer > 0.1){
                font.draw(batch, "Play again", 80, 210);
            }
            font.getData().setScale(1);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("窗口尺寸：" + width + " × " + height);
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        items.dispose();
        background.dispose();
    }
}
