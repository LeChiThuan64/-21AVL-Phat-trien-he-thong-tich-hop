import java.io.File;
// Delete thư mục rỗng 
public class DeleteFolder {
    public boolean deleteEmptyFolder(String source) {
        File folder = new File(source);
        // kiem tra neu folder ton tai thi xoa
        if (folder.exists()) {
            folder.delete();
            System.out.println("folder ton tai\n xoa folder thanh cong");
            return true;
        } else {
            System.out.println("folder khong ton tai");
        }
        return false;
    }
}
