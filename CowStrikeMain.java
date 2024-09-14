public class CowStrikeMain {
    public static void main(String[] args) {
        // สร้างข้อมูลวัวขึ้นมา 10 ตัว
        CowModel[] cows = new CowModel[10];
        
        // เตรียมข้อมูลวัว 10 ตัว โดยจะถูกสร้างใน Controller
        CowView view = new CowView(); // สร้าง View
        new CowController(cows, view); // สร้าง Controller และเชื่อมต่อกับ Model และ View
    }
}

