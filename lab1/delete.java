import java.io.File;

public class delete {
    private void deleteFile(String source)
    {
        File file = new File(source);
        // kiem tra file co ton tai khong
      if(file.exists()){
        System.out.println("file co ton tai");
        file.delete();
        System.out.println("xoa thanh cong");

      }
      else{
        System.out.println("file khongton tai");
        
      }
    }
    public static void main(String[] args) {
        delete d = new delete();
        d.deleteFile("E:/Thuan/test.txt");
    }
}
