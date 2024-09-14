import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CowController {
    private CowModel[] cows;
    private CowView view;
    private Map<String, CowModel> cowData;
    private int milkCount;  // นมจืด
    private int sourMilkCount;  // นมเปรี้ยว
    private int chocolateMilkCount;  // นมช็อกโกแลต
    private int soyMilkCount;  // นมถั่วเหลือง
    private int almondMilkCount;  // นมอัลมอนด์

    public CowController(CowModel[] cows, CowView view) {
        this.cows = cows;
        this.view = view;
        this.cowData = new HashMap<>();
        this.milkCount = 0;
        this.sourMilkCount = 0;
        this.chocolateMilkCount = 0;
        this.soyMilkCount = 0;
        this.almondMilkCount = 0;

        // เตรียมข้อมูลวัว 10 ตัว
        prepareCowData();

        // เพิ่ม ActionListener
        view.addSubmitListener(new SubmitListener());
        view.addMilkListener(new MilkListener());
        view.addFeedLemonListener(new FeedLemonListener());
        view.addResetListener(new ResetListener());
    }

    // ฟังก์ชันเตรียมข้อมูลวัว 10 ตัว
    private void prepareCowData() {
        // ข้อมูลรหัสวัว
        String[] cowIds = {"12345678", "23456789", "34567890", "45678901", "56789012",
                        "67890123", "78901234", "89012345", "90123456", "98765432"};
        
        // ข้อมูลพันธุ์วัว
        String[] cowBreeds = {"White", "Brown", "White", "Brown", "White",
                            "White", "Brown", "Brown", "White", "Brown"};

        // เตรียมข้อมูลวัว 10 ตัว
        for (int i = 0; i < 10; i++) {
            // สุ่มอายุวัวเป็นปีและเดือน
            int[] age = CowModel.randomAge();

            // สร้างวัวแต่ละตัวด้วยข้อมูลที่กำหนด
            CowModel cow = new CowModel(cowIds[i], cowBreeds[i], age[0], age[1]);
            
            // เพิ่มวัวลงใน Map โดยใช้รหัสเป็น Key
            cowData.put(cow.getId(), cow);
        }
    }


    // ฟังก์ชันสุ่มความผิดพลาด BSOD
    private boolean checkForBSOD(CowModel cow) {
        Random random = new Random();
        if (cow.getBreed().equals("White") && !cow.isLemonFed()) {
            double chance = 0.5 * cow.getAgeMonths() / 100.0;  // โอกาสเกิด BSOD
            return random.nextDouble() < chance;
        } else if (cow.getBreed().equals("Brown")) {
            double chance = 1.0 * cow.getAgeYears() / 100.0;  // โอกาสเกิด BSOD
            return random.nextDouble() < chance;
        }
        return false;
    }

    // ฟังก์ชันเพิ่มจำนวนการผลิตนม
    private void addMilk(String cowId, CowModel cow) {
        if (cow.isBSOD()) {
            view.showErrorMessage("This cow is in BSOD state. Please reset.");
            return;
        }

        if (checkForBSOD(cow)) {
            cow.setBSOD(true);  // หากเกิด BSOD
            view.showErrorMessage("The cow has gone BSOD. It is now a blue cow and can't be milked.");
            return;
        }

        if (cow.getBreed().equals("White")) {
            if (cow.isLemonFed()) {
                sourMilkCount++;
                view.displayCowInfo("Cow ID: " + cowId + " produced 1 bottle of sour milk.");
            } else {
                milkCount++;
                view.displayCowInfo("Cow ID: " + cowId + " produced 1 bottle of regular milk.");
            }
        } else if (cow.getBreed().equals("Brown")) {
            chocolateMilkCount++;
            view.displayCowInfo("Cow ID: " + cowId + " produced 1 bottle of chocolate milk.");
        }

        // แสดงรายงานจำนวนขวดนมทั้งหมด
        view.displayCowInfo("Total Regular Milk: " + milkCount +
                            "\nTotal Sour Milk: " + sourMilkCount +
                            "\nTotal Chocolate Milk: " + chocolateMilkCount +
                            "\nTotal Soy Milk (waste): " + soyMilkCount +
                            "\nTotal Almond Milk (waste): " + almondMilkCount);
    }

    // ActionListener สำหรับ Submit
    class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cowId = view.getIdInput(); // รับรหัสจากผู้ใช้
            if (cowId.length() != 8 || !cowId.matches("\\d+")) {  // ตรวจสอบว่ารหัสต้องเป็นตัวเลข 8 หลัก
                view.showErrorMessage("Invalid Cow ID. Please enter an 8-digit numeric ID.");
                return;
            }

            if (cowData.containsKey(cowId)) {  // ตรวจสอบว่ารหัสวัวมีอยู่ในระบบหรือไม่
                CowModel cow = cowData.get(cowId);  // ดึงข้อมูลวัวตามรหัส
                view.displayCowInfo("Cow ID: " + cow.getId() + "\nBreed: " + cow.getBreed() +
                                    "\nAge: " + cow.getAgeYears() + " Years, " + cow.getAgeMonths() + " Months");

                view.enableMilkButton();  // เปิดใช้งานปุ่มรีดนม

                if (cow.getBreed().equals("White")) {
                    view.enableFeedLemonButton();  // เปิดใช้งานปุ่มป้อนมะนาวถ้าเป็นวัวสีขาว
                }
            } else {
                view.showErrorMessage("Cow ID not found. Please check the ID and try again.");
            }
        }
    }        

    // ActionListener สำหรับปุ่มรีดนม
    class MilkListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cowId = view.getIdInput();
            CowModel cow = cowData.get(cowId);
            addMilk(cowId, cow);  // เรียกฟังก์ชันรีดนม

            if (cow.isBSOD()) {
                view.showErrorMessage("This cow is in BSOD state. Please reset.");
                return;
            }
    
            if (checkForBSOD(cow)) {
                cow.setBSOD(true);  // หากเกิด BSOD
                view.showErrorMessage("The cow has gone BSOD. It is now a blue cow and can't be milked.");
                view.enableResetButton();  // เปิดใช้งานปุ่ม Reset
                return;
            }
        }
    }

    // ป้อนมะนาว
    class FeedLemonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cowId = view.getIdInput();
            CowModel cow = cowData.get(cowId);
            if (cow.getBreed().equals("White")) {
                cow.feedLemon();
                view.displayCowInfo("This cow has been fed a lemon and will now produce sour milk.");
            } else {
                view.showErrorMessage("Only white cows can be fed lemons.");
            }
        }
    }

    // ActionListener สำหรับ Reset BSOD
    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (CowModel cow : cowData.values()) {
                if (cow.isBSOD()) {
                    cow.setBSOD(false);  // Reset วัวที่เป็น BSOD
                }
            }
            view.displayCowInfo("All BSOD cows have been reset.");
        }
    }
}
