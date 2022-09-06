# SellHelmet
-----------Hướng dẫn cài đặt và sử dụng website
- Công cụ lập trình: Eclipse IDE 
                     Sql Server 2014
                     Tomcat từ phiên bản 8 trở đi
- Công nghệ sử dụng: Ngôn ngữ java
                     Hệ quản trị CSDL SQL Server
                     Dynamic web dành cho spring mvc
                     Thư viện kết nối CSDL Hibernate
- Hướng dẫn cài đặt: Tải file zip về, giải nén
                     Lấy file BANMUBAOHIEM.bak phục hồi lại CSDL
                     Nằm trong đường dẫn SellHelmet\src\main\webapp\WEB-INF\configs
                      + Trong file spring-config-hibernate.xml  sửa lại username và password cho đúng với tài khoản CSDL hiện có trong máy   
                      + Trong file spring-config-mvc.xml sửa lại đường dẫn có chuỗi D:\PTIT_HCM\ThucTap\ thành chuỗi mới là nơi project được tại xuống và lưu trữ
- Hướng dẫn sử dụng: Chạy project trong elipse.
                        Trang mua hàng là: http://localhost:8080/SellHelmet/User/home.htm
                        Trang quản trị là: http://localhost:8080/SellHelmet/Admin/info.htm
