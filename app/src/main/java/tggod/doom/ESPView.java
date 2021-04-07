package tggod.doom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Process;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ESPView extends View implements Runnable {
    private static int ColorAlert;
    private static int ColorAlertText;
    private static int ColorBox;
    private static int ColorHead;
    private static int ColorLine;
    private static int ColorSkel;
    private static final int[] ITEM_NAME = {R.drawable.i0, R.drawable.i1, R.drawable.i2, R.drawable.i3, R.drawable.i4, R.drawable.i5, R.drawable.i6, R.drawable.i7, R.drawable.i8, R.drawable.i9, R.drawable.i10, R.drawable.i11, R.drawable.i12, R.drawable.i13, R.drawable.i14, R.drawable.i15, R.drawable.i16, R.drawable.i17, R.drawable.i18, R.drawable.i19, R.drawable.i20, R.drawable.i21, R.drawable.i22, R.drawable.i23, R.drawable.i24, R.drawable.i25, R.drawable.i26, R.drawable.i27, R.drawable.i28, R.drawable.i29, R.drawable.i30, R.drawable.i31, R.drawable.i32, R.drawable.i33, R.drawable.i34, R.drawable.i35, R.drawable.i36, R.drawable.i37, R.drawable.i38, R.drawable.i39, R.drawable.i40, R.drawable.i41, R.drawable.i42, R.drawable.i43, R.drawable.i44, R.drawable.i45, R.drawable.i46, R.drawable.i47, R.drawable.i48, R.drawable.i49, R.drawable.i50, R.drawable.i51, R.drawable.i52, R.drawable.i53, R.drawable.i54, R.drawable.i55, R.drawable.i56, R.drawable.i57, R.drawable.i58, R.drawable.i59, R.drawable.i60, R.drawable.i61, R.drawable.i62, R.drawable.i63, R.drawable.i64, R.drawable.i65, R.drawable.i66, R.drawable.i67, R.drawable.i68, R.drawable.i69, R.drawable.i70, R.drawable.i71, R.drawable.i72, R.drawable.i73, R.drawable.i74, R.drawable.i75, R.drawable.i76, R.drawable.i77, R.drawable.i78, R.drawable.i79, R.drawable.i80, R.drawable.i81, R.drawable.i82, R.drawable.i83, R.drawable.i84, R.drawable.i85, R.drawable.i86, R.drawable.i87, R.drawable.i88, R.drawable.i89, R.drawable.i90, R.drawable.i91, R.drawable.i92, R.drawable.i93, R.drawable.i94, R.drawable.i95, R.drawable.i96, R.drawable.i97, R.drawable.i98, R.drawable.i99, R.drawable.i100, R.drawable.i101, R.drawable.i102, R.drawable.i103, R.drawable.i104, R.drawable.i105, R.drawable.i106, R.drawable.i107, R.drawable.i108, R.drawable.i109, R.drawable.i110, R.drawable.i111, R.drawable.i112, R.drawable.i113, R.drawable.i114, R.drawable.i115, R.drawable.i116};
    private static final int[] OTH_NAME = {R.drawable.ic_clear_enemy, R.drawable.ic_clear_boot, R.drawable.ic_danger_enemy, R.drawable.ic_danger_boot, R.drawable.ic_grenade_warning};
    private static int Size;
    private static int Strokebox;
    private static int Strokeline;
    private static int Strokeskel;
    private static final int[] VEH_NAME = {R.drawable.ve1, R.drawable.ve2, R.drawable.ve3, R.drawable.ve4, R.drawable.ve5, R.drawable.ve6, R.drawable.ve7, R.drawable.ve8, R.drawable.ve9, R.drawable.ve10, R.drawable.ve11, R.drawable.ve12, R.drawable.ve13, R.drawable.ve14, R.drawable.ve15, R.drawable.ve16, R.drawable.ve17, R.drawable.ve18};
    private static int bgEnWeapon;
    private static int bgdistance;
    private static int bgid;
    private static int bgname;
    private static int itemPosition;
    private static int itemSize;
    public static long sleepTime;
    private static int textdistance;
    private static int textenweapon;
    private static int textname;
    int FPS = 60;
    private int    mFPS = 0;
    private int    mFPSCounter = 0;
    private long   mFPSTime = 0;
    public Bitmap[] OTHER = new Bitmap[5];
    public Bitmap[] VEH = new Bitmap[20];
    public Bitmap[] WEP = new Bitmap[120];
    public Paint f68p;
    public SimpleDateFormat formatter;
    public Paint mFilledPaint;
    public Paint mFilledPaint2;
    public Paint mFilledPaint3;
    public Paint mStrokePaint;
    public Paint mStrokePaint2;
    public Paint mStrokePaint3;
    public Paint mTextPaint;
    public Paint mTextPaint1;
    public Paint mTextPaint2;
    public Paint mTextPaint3;
    public Paint mTextPaint4;
    public Thread mThread;

    /* renamed from: p */
    public Paint f11302p;
    public Date time;
    private Object Pos;

    public ESPView(Context context) {
        super(context, (AttributeSet) null, 0);
        InitializePaints();
        setFocusableInTouchMode(false);
        setBackgroundColor(0);
        this.time = new Date();
        this.formatter = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        sleepTime = (long) (1000 / this.FPS);
        Thread thread = new Thread(this);
        this.mThread = thread;
        thread.start();
    }

    public static void ChangeBgDist(int i) {
        bgdistance = i;
    }

    public static void ChangeBgEnWeapon(int i) {
        bgEnWeapon = i;
    }

    public static void ChangeBgId(int i) {
        bgid = i;
    }

    public static void ChangeBgName(int i) {
        bgname = i;
    }

    public static void ChangeColorAlert(int i) {
        ColorAlert = i;
    }

    public static void ChangeColorAlertText(int i) {
        ColorAlertText = i;
    }

    public static void ChangeColorBox(int i) {
        ColorBox = i;
    }

    public static void ChangeColorHead(int i) {
        ColorHead = i;
    }

    public static void ChangeColorLine(int i) {
        ColorLine = i;
    }

    public static void ChangeColorSkel(int i) {
        ColorSkel = i;
    }

    public static void ChangeFps(int i) {
        sleepTime = (long) (1000 / (i + 20));
    }



    public static void ChangeSize(int i) {
        Size = i;
    }

    public static void ChangeStrokeBox(int i) {
        Strokebox = i;
    }

    public static void ChangeStrokeLine(int i) {
        Strokeline = i;
    }

    public static void ChangeStrokeSkel(int i) {
        Strokeskel = i;
    }

    public static void ChangeTextDist(int i) {
        textdistance = i;
    }

    public static void ChangeTextEnWeapon(int i) {
        textenweapon = i;
    }

    public static void ChangeTextName(int i) {
        textname = i;
    }

    public static void ChangeitemPosition(int i) {
        itemPosition = i;
    }

    public static void ChangeitemSize(int i) {
        itemSize = i;
    }

    private String getItemName(String str) {
        return null;
    }

    private int getItemNum(String str) {
        if (str.contains("MZJ_8X") && Overlay.getConfig("8x")) {
            this.mTextPaint.setARGB(255, 247, 99, 245);
            return 0;
        } else if (str.contains("MZJ_2X") && Overlay.getConfig("2x")) {
            this.mTextPaint.setARGB(255, 230, 172, 226);
            return 1;
        } else if (str.contains("MZJ_HD") && Overlay.getConfig("Red Dot")) {
            this.mTextPaint.setARGB(255, 230, 172, 226);
            return 2;
        } else if (str.contains("MZJ_3X") && Overlay.getConfig("3x")) {
            this.mTextPaint.setARGB(255, 247, 99, 245);
            return 3;
        } else if (str.contains("MZJ_QX") && Overlay.getConfig("Hollow")) {
            this.mTextPaint.setARGB(255, 153, 75, 152);
            return 4;
        } else if (str.contains("MZJ_6X") && Overlay.getConfig("6x")) {
            this.mTextPaint.setARGB(255, 247, 99, 245);
            return 5;
        } else if (str.contains("MZJ_4X") && Overlay.getConfig("4x")) {
            this.mTextPaint.setARGB(255, 247, 99, 245);
            return 6;
        } else if (str.contains("MZJ_SideRMR") && Overlay.getConfig("Canted")) {
            this.mTextPaint.setARGB(255, 153, 75, 152);
            return 7;
        } else if (str.contains("AUG") && Overlay.getConfig("AUG")) {
            this.mTextPaint.setARGB(255, 52, 224, 63);
            return 8;
        } else if (str.contains("M762") && Overlay.getConfig("M762")) {
            this.mTextPaint.setARGB(255, 43, 26, 28);
            return 9;
        } else if (str.contains("SCAR") && Overlay.getConfig("SCAR-L")) {
            this.mTextPaint.setARGB(255, 52, 224, 63);
            return 10;
        } else if (str.contains("M416") && Overlay.getConfig("M416")) {
            this.mTextPaint.setARGB(255, 115, 235, 223);
            return 11;
        } else if (str.contains("M16A4") && Overlay.getConfig("M16A4")) {
            this.mTextPaint.setARGB(255, 116, 227, 123);
            return 12;
        } else if (str.contains("Mk47") && Overlay.getConfig("Mk47 Mutant")) {
            this.mTextPaint.setARGB(255, 247, 99, 245);
            return 13;
        } else if (str.contains("G36") && Overlay.getConfig("G36C")) {
            this.mTextPaint.setARGB(255, 116, 227, 123);
            return 14;
        } else if (str.contains("QBZ") && Overlay.getConfig("QBZ")) {
            this.mTextPaint.setARGB(255, 52, 224, 63);
            return 15;
        } else if (str.contains("AKM") && Overlay.getConfig("AKM")) {
            this.mTextPaint.setARGB(255, 214, 99, 99);
            return 16;
        } else if (str.contains("Groza") && Overlay.getConfig("Groza")) {
            this.mTextPaint.setARGB(255, 214, 99, 99);
            return 17;
        } else if (str.contains("PP19") && Overlay.getConfig("Bizon")) {
            this.mTextPaint.setARGB(255, 255, 246, 0);
            return 18;
        } else if (str.contains("TommyGun") && Overlay.getConfig("TommyGun")) {
            this.mTextPaint.setARGB(255, 207, 207, 207);
            return 19;
        } else if (str.contains("MP5K") && Overlay.getConfig("MP5K")) {
            this.mTextPaint.setARGB(255, 207, 207, 207);
            return 20;
        } else if (str.contains("UMP9") && Overlay.getConfig("UMP")) {
            this.mTextPaint.setARGB(255, 207, 207, 207);
            return 21;
        } else if (str.contains("Vector") && Overlay.getConfig("Vector")) {
            this.mTextPaint.setARGB(255, 255, 246, 0);
            return 22;
        } else if (str.contains("MachineGun_Uzi") && Overlay.getConfig("Uzi")) {
            this.mTextPaint.setARGB(255, 255, 246, 0);
            return 23;
        } else if (str.contains("DP28") && Overlay.getConfig("DP28")) {
            this.mTextPaint.setARGB(255, 43, 26, 28);
            return 24;
        } else if (str.contains("M249") && Overlay.getConfig("M249")) {
            this.mTextPaint.setARGB(255, 247, 99, 245);
            return 25;
        } else if (str.contains("AWM") && Overlay.getConfig("AWM")) {
            this.mTextPaint.setColor(-16777216);
            return 26;
        } else if (str.contains("QBU") && Overlay.getConfig("QBU")) {
            this.mTextPaint.setARGB(255, 207, 207, 207);
            return 27;
        } else if (str.contains("SLR") && Overlay.getConfig("SLR")) {
            this.mTextPaint.setARGB(255, 43, 26, 28);
            return 28;
        } else if (str.contains("SKS") && Overlay.getConfig("SKS")) {
            this.mTextPaint.setARGB(255, 43, 26, 28);
            return 29;
        } else if (str.contains("Mini14") && Overlay.getConfig("Mini14")) {
            this.mTextPaint.setARGB(255, 247, 99, 245);
            return 30;
        } else if (str.contains("M24") && Overlay.getConfig("M24")) {
            this.mTextPaint.setARGB(255, 247, 99, 245);
            return 31;
        } else if (str.contains("Kar98k") && Overlay.getConfig("Kar98k")) {
            this.mTextPaint.setARGB(255, 247, 99, 245);
            return 32;
        } else if (str.contains("VSS") && Overlay.getConfig("VSS")) {
            this.mTextPaint.setARGB(255, 255, 246, 0);
            return 33;
        } else if (str.contains("Win94") && Overlay.getConfig("Win94")) {
            this.mTextPaint.setARGB(255, 207, 207, 207);
            return 34;
        } else if (str.contains("Mk14") && Overlay.getConfig("Mk14")) {
            this.mTextPaint.setColor(-16777216);
            return 35;
        } else if (str.contains("S12K") && Overlay.getConfig("S12K")) {
            this.mTextPaint.setARGB(255, 153, 109, 109);
            return 36;
        } else if (str.contains("DBS") && Overlay.getConfig("DBS")) {
            this.mTextPaint.setARGB(255, 153, 109, 109);
            return 37;
        } else if (str.contains("S686") && Overlay.getConfig("S686")) {
            this.mTextPaint.setARGB(255, 153, 109, 109);
            return 38;
        } else if (str.contains("S1897") && Overlay.getConfig("S1897")) {
            this.mTextPaint.setARGB(255, 153, 109, 109);
            return 39;
        } else if (str.contains("Sickle") && Overlay.getConfig("Sickle")) {
            this.mTextPaint.setARGB(255, 102, 74, 74);
            return 40;
        } else if (str.contains("Machete") && Overlay.getConfig("Machete")) {
            this.mTextPaint.setARGB(255, 102, 74, 74);
            return 41;
        } else if (str.contains("Cowbar") && Overlay.getConfig("Cowbar")) {
            this.mTextPaint.setARGB(255, 102, 74, 74);
            return 42;
        } else if (str.contains("CrossBow") && Overlay.getConfig("CrossBow")) {
            this.mTextPaint.setARGB(255, 102, 74, 74);
            return 43;
        } else if (str.contains("Pan") && Overlay.getConfig("Pan")) {
            this.mTextPaint.setARGB(255, 102, 74, 74);
            return 44;
        } else if (str.contains("SawedOff") && Overlay.getConfig("SawedOff")) {
            this.mTextPaint.setARGB(255, 156, 113, 81);
            return 45;
        } else if (str.contains("R1895") && Overlay.getConfig("R1895")) {
            this.mTextPaint.setARGB(255, 156, 113, 81);
            return 46;
        } else if (str.contains("Vz61") && Overlay.getConfig("Vz61")) {
            this.mTextPaint.setARGB(255, 156, 113, 81);
            return 47;
        } else if (str.contains("P92") && Overlay.getConfig("P92")) {
            this.mTextPaint.setARGB(255, 156, 113, 81);
            return 48;
        } else if (str.contains("P18C") && Overlay.getConfig("P18C")) {
            this.mTextPaint.setARGB(255, 156, 113, 81);
            return 49;
        } else if (str.contains("R45") && Overlay.getConfig("R45")) {
            this.mTextPaint.setARGB(255, 156, 113, 81);
            return 50;
        } else if (str.contains("P1911") && Overlay.getConfig("P1911")) {
            this.mTextPaint.setARGB(255, 156, 113, 81);
            return 51;
        } else if (str.contains("DesertEagle") && Overlay.getConfig("Desert Eagle")) {
            this.mTextPaint.setARGB(255, 156, 113, 81);
            return 52;
        } else if (str.contains("Ammo_762mm") && Overlay.getConfig("7.62")) {
            this.mTextPaint.setARGB(255, 92, 36, 28);
            return 53;
        } else if (str.contains("Ammo_45AC") && Overlay.getConfig("45ACP")) {
            this.mTextPaint.setColor(-3355444);
            return 54;
        } else if (str.contains("Ammo_556mm") && Overlay.getConfig("5.56")) {
            this.mTextPaint.setColor(-16711936);
            return 55;
        } else if (str.contains("Ammo_9mm") && Overlay.getConfig("9mm")) {
            this.mTextPaint.setColor(-256);
            return 56;
        } else if (str.contains("Ammo_300Magnum") && Overlay.getConfig("300")) {
            this.mTextPaint.setColor(-16777216);
            return 57;
        } else if (str.contains("Ammo_12Guage") && Overlay.getConfig("12 Guage")) {
            this.mTextPaint.setARGB(255, 156, 91, 81);
            return 58;
        } else if (str.contains("Ammo_Bolt") && Overlay.getConfig("Arrow")) {
            this.mTextPaint.setARGB(255, 156, 113, 81);
            return 59;
        } else if (str.contains("Bag_Lv3") && Overlay.getConfig("BagLv3")) {
            this.mTextPaint.setARGB(255, 36, 83, 255);
            return 60;
        } else if (str.contains("Bag_Lv1") && Overlay.getConfig("BagLv1")) {
            this.mTextPaint.setARGB(255, 127, 154, 250);
            return 61;
        } else if (str.contains("Bag_Lv2") && Overlay.getConfig("BagLv2")) {
            this.mTextPaint.setARGB(255, 77, 115, 255);
            return 62;
        } else if (str.contains("Armor_Lv2") && Overlay.getConfig("VestLv2")) {
            this.mTextPaint.setARGB(255, 77, 115, 255);
            return 63;
        } else if (str.contains("Armor_Lv1") && Overlay.getConfig("VestLv1")) {
            this.mTextPaint.setARGB(255, 127, 154, 250);
            return 64;
        } else if (str.contains("Armor_Lv3") && Overlay.getConfig("VestLv3")) {
            this.mTextPaint.setARGB(255, 36, 83, 255);
            return 65;
        } else if (str.contains("Helmet_Lv2") && Overlay.getConfig("HelmetLv2")) {
            this.mTextPaint.setARGB(255, 77, 115, 255);
            return 66;
        } else if (str.contains("Helmet_Lv1") && Overlay.getConfig("HelmetLv1")) {
            this.mTextPaint.setARGB(255, 127, 154, 250);
            return 67;
        } else if (str.contains("Helmet_Lv3") && Overlay.getConfig("HelmetLv3")) {
            this.mTextPaint.setARGB(255, 36, 83, 255);
            return 68;
        } else if (str.contains("Pills") && Overlay.getConfig("PainKiller")) {
            this.mTextPaint.setARGB(255, 227, 91, 54);
            return 69;
        } else if (str.contains("Injection") && Overlay.getConfig("Adrenaline")) {
            this.mTextPaint.setARGB(255, 204, 193, 190);
            return 70;
        } else if (str.contains("Drink") && Overlay.getConfig("Energy Drink")) {
            this.mTextPaint.setARGB(255, 54, 175, 227);
            return 71;
        } else if (str.contains("Firstaid") && Overlay.getConfig("FirstAidKit")) {
            this.mTextPaint.setARGB(255, 194, 188, 109);
            return 72;
        } else if (str.contains("Bandage") && Overlay.getConfig("Bandage")) {
            this.mTextPaint.setARGB(255, 43, 189, 48);
            return 73;
        } else if (str.contains("FirstAidbox") && Overlay.getConfig("Medkit")) {
            this.mTextPaint.setARGB(255, 0, 171, 6);
            return 74;
        } else if (str.contains("Grenade_Stun") && Overlay.getConfig("Stung")) {
            this.mTextPaint.setARGB(255, 204, 193, 190);
            return 75;
        } else if (str.contains("Grenade_Shoulei") && Overlay.getConfig("Grenade")) {
            this.mTextPaint.setARGB(255, 2, 77, 4);
            return 76;
        } else if (str.contains("Grenade_Smoke") && Overlay.getConfig("Smoke")) {
            this.mTextPaint.setColor(-1);
            return 77;
        } else if (str.contains("Grenade_Burn") && Overlay.getConfig("Molotov")) {
            this.mTextPaint.setARGB(255, 230, 175, 64);
            return 78;
        } else if (str.contains("Large_FlashHider") && Overlay.getConfig("Flash Hider Ar")) {
            this.mTextPaint.setARGB(255, 255, 213, 130);
            return 79;
        } else if (str.contains("QK_Large_C") && Overlay.getConfig("Ar Compensator")) {
            this.mTextPaint.setARGB(255, 255, 213, 130);
            return 80;
        } else if (str.contains("Mid_FlashHider") && Overlay.getConfig("Flash Hider SMG")) {
            this.mTextPaint.setARGB(255, 255, 213, 130);
            return 81;
        } else if (str.contains("QT_A_") && Overlay.getConfig("Tactical Stock")) {
            this.mTextPaint.setARGB(255, 158, 222, 195);
            return 82;
        } else if (str.contains("DuckBill") && Overlay.getConfig("Duckbill")) {
            this.mTextPaint.setARGB(255, 158, 222, 195);
            return 83;
        } else if (str.contains("Sniper_FlashHider") && Overlay.getConfig("Flash Hider Snp")) {
            this.mTextPaint.setARGB(255, 158, 222, 195);
            return 84;
        } else if (str.contains("Mid_Suppressor") && Overlay.getConfig("Suppressor SMG")) {
            this.mTextPaint.setARGB(255, 158, 222, 195);
            return 85;
        } else if (str.contains("HalfGrip") && Overlay.getConfig("Half Grip")) {
            this.mTextPaint.setARGB(255, 155, 189, 222);
            return 86;
        } else if (str.contains("Choke") && Overlay.getConfig("Choke")) {
            this.mTextPaint.setARGB(255, 155, 189, 222);
            return 87;
        } else if (str.contains("QT_UZI") && Overlay.getConfig("Stock Micro UZI")) {
            this.mTextPaint.setARGB(255, 155, 189, 222);
            return 88;
        } else if (str.contains("QK_Sniper") && Overlay.getConfig("SniperCompensator")) {
            this.mTextPaint.setARGB(255, 60, 127, 194);
            return 89;
        } else if (str.contains("Sniper_Suppressor") && Overlay.getConfig("Sup Sniper")) {
            this.mTextPaint.setARGB(255, 60, 127, 194);
            return 90;
        } else if (str.contains("Large_Suppressor") && Overlay.getConfig("Suppressor Ar")) {
            this.mTextPaint.setARGB(255, 60, 127, 194);
            return 91;
        } else if (str.contains("Sniper_EQ_") && Overlay.getConfig("Ex.Qd.Sniper")) {
            this.mTextPaint.setARGB(255, 193, 140, 222);
            return 92;
        } else if (str.contains("Mid_Q_") && Overlay.getConfig("Qd.SMG")) {
            this.mTextPaint.setARGB(255, 193, 163, 209);
            return 93;
        } else if (str.contains("Mid_E_") && Overlay.getConfig("Ex.SMG")) {
            this.mTextPaint.setARGB(255, 193, 163, 209);
            return 94;
        } else if (str.contains("Sniper_Q_") && Overlay.getConfig("Qd.Sniper")) {
            this.mTextPaint.setARGB(255, 193, 163, 209);
            return 95;
        } else if (str.contains("Sniper_E_") && Overlay.getConfig("Ex.Sniper")) {
            this.mTextPaint.setARGB(255, 193, 163, 209);
            return 96;
        } else if (str.contains("Large_E_") && Overlay.getConfig("Ex.Ar")) {
            this.mTextPaint.setARGB(255, 193, 163, 209);
            return 97;
        } else if (str.contains("Large_EQ_") && Overlay.getConfig("Ex.Qd.Ar")) {
            this.mTextPaint.setARGB(255, 193, 140, 222);
            return 99;
        } else if (str.contains("Large_Q_") && Overlay.getConfig("Qd.Ar")) {
            this.mTextPaint.setARGB(255, 193, 163, 209);
            return 100;
        } else if (str.contains("Mid_EQ_") && Overlay.getConfig("Ex.Qd.SMG")) {
            this.mTextPaint.setARGB(255, 193, 140, 222);
            return 101;
        } else if (str.contains("Crossbow_Q") && Overlay.getConfig("Quiver CrossBow")) {
            this.mTextPaint.setARGB(255, 148, 121, 163);
            return 102;
        } else if (str.contains("ZDD_Sniper") && Overlay.getConfig("Bullet Loop")) {
            this.mTextPaint.setARGB(255, 148, 121, 163);
            return 103;
        } else if (str.contains("ThumbGrip") && Overlay.getConfig("Thumb Grip")) {
            this.mTextPaint.setARGB(255, 148, 121, 163);
            return 104;
        } else if (str.contains("Lasersight") && Overlay.getConfig("Laser Sight")) {
            this.mTextPaint.setARGB(255, 148, 121, 163);
            return 105;
        } else if (str.contains("Angled") && Overlay.getConfig("Angled Grip")) {
            this.mTextPaint.setARGB(255, 219, 219, 219);
            return 106;
        } else if (str.contains("LightGrip") && Overlay.getConfig("Light Grip")) {
            this.mTextPaint.setARGB(255, 219, 219, 219);
            return 107;
        } else if (str.contains("Vertical") && Overlay.getConfig("Vertical Grip")) {
            this.mTextPaint.setARGB(255, 219, 219, 219);
            return 108;
        } else if (str.contains("GasCan") && Overlay.getConfig("Gas Can")) {
            this.mTextPaint.setARGB(255, 255, 143, 203);
            return 109;
        } else if (str.contains("Mid_Compensator") && Overlay.getConfig("Compensator SMG")) {
            this.mTextPaint.setARGB(255, 219, 219, 219);
            return 110;
        } else if (str.contains("Flare") && Overlay.getConfig("FlareGun")) {
            this.mTextPaint.setARGB(255, 242, 63, 159);
            return 111;
        } else if (str.contains("Ghillie") && Overlay.getConfig("GhillieSuit")) {
            this.mTextPaint.setARGB(255, 139, 247, 67);
            return 112;
        } else if (str.contains("CheekPad") && Overlay.getConfig("CheekPad")) {
            this.mTextPaint.setARGB(255, 112, 55, 55);
            return 113;
        } else if (str.contains("PickUpListWrapperActor") && Overlay.getConfig("Crate")) {
            this.mTextPaint.setARGB(255, 132, 201, 66);
            return 114;
        } else if (str.contains("AirDropPlane") && Overlay.getConfig("DropPlane")) {
            this.mTextPaint.setARGB(255, 224, 177, 224);
            return 115;
        } else if (str.contains("AirDrop") && Overlay.getConfig("AirDrop")) {
            return 116;
        } else {
            if (!str.contains("SpikeTrap") || !Overlay.getConfig("SpikeTrap")) {
                this.mTextPaint.setARGB(255, 255, 10, 255);
                return -1;
            }
            this.mTextPaint.setARGB(255, 132, 201, 66);
            return 117;
        }
    }

    private int getVehicleName(String str) {
        if (str.contains("Buggy") && Overlay.getConfig("Buggy")) {
            return 0;
        }
        if (str.contains("UAZ") && Overlay.getConfig("UAZ")) {
            return 1;
        }
        if (str.contains("MotorcycleC") && Overlay.getConfig("Trike")) {
            return 2;
        }
        if (str.contains("Motorcycle") && Overlay.getConfig("Bike")) {
            return 3;
        }
        if (str.contains("Dacia") && Overlay.getConfig("Dacia")) {
            return 4;
        }
        if (str.contains("AquaRail") && Overlay.getConfig("Jet")) {
            return 5;
        }
        if (str.contains("PG117") && Overlay.getConfig("Boat")) {
            return 6;
        }
        if (str.contains("MiniBus") && Overlay.getConfig("Bus")) {
            return 7;
        }
        if (str.contains("Mirado") && Overlay.getConfig("Mirado")) {
            return 8;
        }
        if (str.contains("Scooter") && Overlay.getConfig("Scooter")) {
            return 9;
        }
        if (str.contains("Rony") && Overlay.getConfig("Rony")) {
            return 10;
        }
        if (str.contains("Snowbike") && Overlay.getConfig("Snowbike")) {
            return 11;
        }
        if (str.contains("Snowmobile") && Overlay.getConfig("Snowmobile")) {
            return 12;
        }
        if (str.contains("Tuk") && Overlay.getConfig("Tempo")) {
            return 13;
        }
        if (str.contains("PickUp") && Overlay.getConfig("Truck")) {
            return 14;
        }
        if (str.contains("BRDM") && Overlay.getConfig("BRDM")) {
            return 15;
        }
        if (!str.contains("LadaNiva") || !Overlay.getConfig("LadaNiva")) {
            return (!str.contains("Bigfoot") || !Overlay.getConfig("Monster Truck")) ? -1 : 17;
        }
        return 16;
    }

    private String getWeapon(int i) {
        if (i == 101006) {
            return "AUG";
        }
        if (i == 101008) {
            return "M762";
        }
        if (i == 101003) {
            return "SCAR-L";
        }
        if (i == 101004) {
            return "M416";
        }
        if (i == 101002) {
            return "M16A-4";
        }
        if (i == 101009) {
            return "Mk47 Mutant";
        }
        if (i == 101010) {
            return "G36C";
        }
        if (i == 101007) {
            return "QBZ";
        }
        if (i == 101001) {
            return "AKM";
        }
        if (i == 101005) {
            return "Groza";
        }
        if (i == 102005) {
            return "Bizon";
        }
        if (i == 102004) {
            return "TommyGun";
        }
        if (i == 102007) {
            return "MP5K";
        }
        if (i == 102002) {
            return "UMP";
        }
        if (i == 102003) {
            return "Vector";
        }
        if (i == 102001) {
            return "Uzi";
        }
        if (i == 105002) {
            return "DP28";
        }
        if (i == 105001) {
            return "M249";
        }
        if (i == 103003) {
            return "AWM";
        }
        if (i == 103010) {
            return "QBU";
        }
        if (i == 103009) {
            return "SLR";
        }
        if (i == 103004) {
            return "SKS";
        }
        if (i == 103006) {
            return "Mini14";
        }
        if (i == 103002) {
            return "M24";
        }
        if (i == 103001) {
            return "Kar98k";
        }
        if (i == 103005) {
            return "VSS";
        }
        if (i == 103008) {
            return "Win94";
        }
        if (i == 103007) {
            return "Mk14";
        }
        if (i == 104003) {
            return "S12K";
        }
        if (i == 104004) {
            return "DBS";
        }
        if (i == 104001) {
            return "S686";
        }
        if (i == 104002) {
            return "S1897";
        }
        if (i == 108003) {
            return "Sickle";
        }
        if (i == 108001) {
            return "Machete";
        }
        if (i == 108002) {
            return "Crowbar";
        }
        if (i == 107001) {
            return "CrossBow";
        }
        if (i == 108004) {
            return "Pan";
        }
        if (i == 106006) {
            return "SawedOff";
        }
        if (i == 106003) {
            return "R1895";
        }
        if (i == 106008) {
            return "Vz61";
        }
        if (i == 106001) {
            return "P92";
        }
        if (i == 106004) {
            return "P18C";
        }
        if (i == 106005) {
            return "R45";
        }
        if (i == 106002) {
            return "P1911";
        }
        if (i == 106010) {
            return "DesertEagle";
        }
        return null;
    }

    public static Bitmap scale(Bitmap bitmap, int i, int i2) {
        float f = (float) i;
        float f2 = (float) i2;
        if (((float) bitmap.getWidth()) / f >= ((float) bitmap.getHeight()) / f2) {
            i2 = (int) ((f / ((float) bitmap.getWidth())) * ((float) bitmap.getHeight()));
        } else {
            i = (int) ((f2 / ((float) bitmap.getHeight())) * ((float) bitmap.getWidth()));
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        float f3 = (float) i;
        float width = f3 / ((float) bitmap.getWidth());
        float f4 = (float) i2;
        float height = f4 / ((float) bitmap.getHeight());
        float f5 = f3 / 2.0f;
        float f6 = f4 / 2.0f;
        Matrix matrix = new Matrix();
        matrix.setScale(width, height, f5, f6);
        Canvas canvas = new Canvas(createBitmap);
        canvas.setMatrix(matrix);
        canvas.drawBitmap(bitmap, f5 - ((float) (bitmap.getWidth() / 2)), f6 - ((float) (bitmap.getHeight() / 2)), new Paint(2));
        return createBitmap;
    }

    public void ClearCanvas(Canvas canvas) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
    }

    public void DebugText(String str) {
        System.out.println(str);
    }

    public void DrawCircle(Canvas canvas, int i, int i2, int i3, int i4, float f, float f2, float f3, float f4) {
        this.mStrokePaint.setARGB(i, i2, i3, i4);
        this.mStrokePaint.setStrokeWidth(f4);
        canvas.drawCircle(f, f2, f3, this.mStrokePaint);
    }

    public void DrawFPS(Canvas cvs, float posX, float posY) {
        mTextPaint.setColor(Color.YELLOW);
        mTextPaint.setShadowLayer(5,0,0, Color.BLACK);
        mTextPaint.setTextSize(30);
        if (SystemClock.uptimeMillis() - mFPSTime > 1000) {
            mFPSTime = SystemClock.uptimeMillis();
            mFPS = mFPSCounter;
            mFPSCounter = 0;
        } else {
            mFPSCounter++;
        }
        cvs.drawText("FPS " + mFPS, posX, posY, mTextPaint);
    }

    public void DrawFilledCircle(Canvas canvas, int i, int i2, int i3, int i4, float f, float f2, float f3) {
        this.mFilledPaint.setColor(Color.rgb(i2, i3, i4));
        this.mFilledPaint.setAlpha(i);
        canvas.drawCircle(f, f2, f3, this.mFilledPaint);
    }

    public void DrawFilledRect(Canvas canvas, int i, int i2, int i3, int i4, float f, float f2, float f3, float f4) {
        this.mFilledPaint.setColor(Color.rgb(i2, i3, i4));
        this.mFilledPaint.setAlpha(i);
        canvas.drawRect(f, f2, f3, f4, this.mFilledPaint);
    }

    public void DrawFilledRect1(Canvas canvas, int i, int i2, int i3, int i4, float f, float f2, float f3, float f4) {
        this.mFilledPaint.setColor(Color.rgb(i2, i3, i4));
        this.mFilledPaint.setAlpha(i);
        canvas.drawRect(f, f2, f + f3, f2 + f4, this.mFilledPaint);
    }

    public void DrawHead(Canvas canvas, int i, int i2, int i3, int i4, float f, float f2, float f3) {
        this.mFilledPaint.setColor(Color.rgb(i2, i3, i4));
        this.mFilledPaint.setAlpha(i);
        this.mFilledPaint.setColor(ColorHead);
        canvas.drawCircle(f, f2, f3, this.mFilledPaint);
    }

    public void DrawItems(Canvas canvas, String str, float f, float f2, float f3, float f4) {
        String itemName = getItemName(str);
        int itemNum = getItemNum(str);
        this.mTextPaint.setTextSize(f4 + ((float) Size));
        if (itemNum != -1) {
            canvas.drawBitmap(this.WEP[itemNum], f2 - 30.0f, ((f3 - 60.0f) - 30.0f) - ((float) Pos), this.f11302p);
            canvas.drawText("(" + Math.round(f) + "m)", f2, (f3 - 10.0f) - ((float) Pos), this.mTextPaint);
        }

        }


    public void DrawLine(Canvas canvas, int i, int i2, int i3, int i4, float f, float f2, float f3, float f4, float f5) {
        this.mStrokePaint.setColor(Color.rgb(i2, i3, i4));
        this.mStrokePaint.setAlpha(i);
        this.mStrokePaint.setStrokeWidth(f);
        canvas.drawLine(f2, f3, f4, f5, this.mStrokePaint);
    }

    public void DrawName(Canvas canvas, int i, int i2, int i3, int i4, String str, int i5, float f, float f2, float f3) {
        String[] split = str.split(":");
        char[] cArr = new char[split.length];
        for (int i6 = 0; i6 < split.length; i6++) {
            cArr[i6] = (char) Integer.parseInt(split[i6]);
        }
        String str2 = new String(cArr);
        this.mTextPaint.setARGB(i, i2, i3, i4);
        this.mTextPaint.setTextSize(f3 + ((float) Size));
        canvas.drawText(i5 + ". " + str2, f, f2, this.mTextPaint);
    }

    public void DrawRect(Canvas canvas, int i, int i2, int i3, int i4, float f, float f2, float f3, float f4, float f5) {
        this.mStrokePaint.setStrokeWidth(f);
        this.mStrokePaint.setColor(Color.rgb(i2, i3, i4));
        this.mStrokePaint.setAlpha(i);
        canvas.drawRect(f2, f3, f4, f5, this.mStrokePaint);
    }

    public void DrawText(Canvas canvas, int i, int i2, int i3, int i4, String str, float f, float f2, float f3) {
        this.mTextPaint.setARGB(i, i2, i3, i4);
        this.mTextPaint.setTextSize(f3);
        canvas.drawText(str, f, f2, this.mTextPaint);
    }

    public void DrawText2(Canvas canvas, int i, int i2, int i3, int i4, String str, float f, float f2, float f3) {
        this.mTextPaint.setARGB(i, i2, i3, i4);
        this.mTextPaint.setTextSize(f3 + ((float) Size));
        canvas.drawText(str, f, f2 - ((float) Pos), this.mTextPaint);
    }

    public void DrawVehicles(Canvas canvas, String str, float f, float f2, float f3, float f4) {
        int vehicleName = getVehicleName(str);
        this.mTextPaint.setColor(-256);
        this.mTextPaint.setAlpha(150);
        this.mTextPaint.setTextSize(f4);
        if (vehicleName != -1) {
            canvas.drawBitmap(this.VEH[vehicleName], f2 - 20.0f, ((f3 - 40.0f) - 30.0f) - ((float) Pos), this.f11302p);
            canvas.drawText("(" + Math.round(f) + "m)", f2, f3 - 10.0f, this.mTextPaint);
        }
    }

    public void DrawWeapon(Canvas canvas, int i, int i2, int i3, int i4, int i5, int i6, float f, float f2, float f3) {
        this.mTextPaint.setARGB(i, i2, i3, i4);
        this.mTextPaint.setTextSize(f3 + ((float) Size));
        String weapon = getWeapon(i5);
        if (weapon != null) {
            canvas.drawText(weapon + ": " + i6, f, f2, this.mTextPaint);
        }
    }

    public void InitializePaints() {
        Paint paint = new Paint();
        this.mStrokePaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.mStrokePaint.setAntiAlias(true);
        this.mStrokePaint.setColor(Color.rgb(0, 0, 0));
        Paint paint2 = new Paint();
        this.mFilledPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.mFilledPaint.setAntiAlias(true);
        this.mFilledPaint.setColor(Color.rgb(0, 0, 0));
        Paint paint3 = new Paint();
        this.mTextPaint = paint3;
        paint3.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setColor(Color.rgb(0, 0, 0));
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mTextPaint.setStrokeWidth(1.1f);
        this.f11302p = new Paint();
        int length = VEH_NAME.length;
        for (int i = 0; i < length; i++) {
            this.VEH[i] = BitmapFactory.decodeResource(getResources(), VEH_NAME[i]);
            Bitmap[] bitmapArr = this.VEH;
            bitmapArr[i] = scale(bitmapArr[i], 40, 40);
        }
        int length2 = ITEM_NAME.length;
        for (int i2 = 0; i2 < length2; i2++) {
            this.WEP[i2] = BitmapFactory.decodeResource(getResources(), ITEM_NAME[i2]);
            Bitmap[] bitmapArr2 = this.WEP;
            bitmapArr2[i2] = scale(bitmapArr2[i2], 60, 60);
        }
    }

    public void onDraw(Canvas canvas) {
        if (canvas != null && getVisibility() == 0) {
            ClearCanvas(canvas);
            Overlay.DrawOn(this, canvas);
        }
    }

    public void run() {
        Process.setThreadPriority(10);
        while (this.mThread.isAlive() && !this.mThread.isInterrupted()) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                postInvalidate();
                Thread.sleep(Math.max(Math.min(0, sleepTime - (System.currentTimeMillis() - currentTimeMillis)), sleepTime));
            } catch (Exception unused) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
