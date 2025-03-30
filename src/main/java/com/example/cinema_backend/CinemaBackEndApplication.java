package com.example.cinema_backend;

import com.example.cinema_backend.entities.*;
import com.example.cinema_backend.repositories.*;
import com.example.cinema_backend.services.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootApplication
public class CinemaBackEndApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    @Autowired
    private IUserService userService;

    @Autowired
    private IMovieRepository movieRepository;

    @Autowired
    private IBranchRepository branchRepository;

    @Autowired
    private IRoomRepository roomRepository;

    @Autowired
    private IScheduleRepository scheduleRepository;

    @Autowired
    private ISeatRepository seatRepository;

    // Do chưa có trang admin để thêm phim và lịch chiếu nên thêm tạm dữ liệu xuống db để demo
    @PostConstruct
    public void init() {
        // Chạy 1 lần đầu application rồi bỏ comment(//) đoạn dưới này rồi chạy lại thêm 1 lần nữa để add data ghế ngồi cho room
        Room room = roomRepository.findById(1).get();

        for(int i=1;i<=8;i++){
            Seat seat = new Seat();
            seat.setName("A"+i);
            seat.setRoom(room);
            seatRepository.save(seat);
        }

        for(int i=1;i<=8;i++){
            Seat seat = new Seat();
            seat.setName("B"+i);
            seat.setRoom(room);
            seatRepository.save(seat);
        }
        for(int i=1;i<=8;i++){
            Seat seat = new Seat();
            seat.setName("C"+i);
            seat.setRoom(room);
            seatRepository.save(seat);
        }
        for(int i=1;i<=8;i++){
            Seat seat = new Seat();
            seat.setName("D"+i);
            seat.setRoom(room);
            seatRepository.save(seat);
        }
        for(int i=1;i<=8;i++){
            Seat seat = new Seat();
            seat.setName("E"+i);
            seat.setRoom(room);
            seatRepository.save(seat);
        }

        List<Movie> movies = movieRepository.findAll();
        if (movies.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Movie aiOan = addNewMovie("AI OÁN TRONG VƯỜN XUÂN","https://iguov8nhvyobj.vcdn.cloud/media/catalog/product/cache/3/image/c5f0a1eff4c394a251036189ccddaacd/p/o/poster_ai_oan_trong_vuon_xuan_4_1_.jpg",
                    "Bí ẩn trong khu vườn đẹp đẽ hé lộ những uẩn khúc kinh hoàng.",
                    "Một ngôi nhà cổ trong khu vườn mùa xuân yên bình dường như ẩn chứa điều gì đó không yên. Khi gia đình mới dọn đến khám phá khuôn viên, những sự kiện kỳ bí và ghê rợn bắt đầu xuất hiện, dẫn họ đến với các bí mật đen tối tưởng chừng bị chôn vùi mãi mãi.",
                    "https://i.ytimg.com/vi/iE6jbWVNh6w/sddefault.jpg",
                    "Koo Tae-jin","Jo Yoon-Hee, Kim Joo-Ryeong, Jung In-Gyeom, Heo Dong-Won",
                    "Hồi hộp, Kinh Dị",LocalDate.parse("08/11/2024",formatter),
                    91,"https://www.youtube.com/embed/QeDo1uPujSc",
                    "Tiếng Hàn - Phụ đề tiếng Việt, tiếng Anh",
                    "T18 - Phim được phổ biến đến người xem từ đủ 18 tuổi trở lên (18+)",1);
            Movie venom = addNewMovie("VENOM: KÈO CUỐI","https://iguov8nhvyobj.vcdn.cloud/media/catalog/product/cache/3/image/c5f0a1eff4c394a251036189ccddaacd/r/s/rsz_vnm3_intl_online_1080x1350_tsr_01.jpg",
                    "Venom trở lại với một đối thủ đáng gờm và trận chiến cuối cùng đầy kịch tính.",
                    "Venom phải đối mặt với thách thức cam go nhất từ trước đến nay khi một kẻ thù mới xuất hiện, mạnh mẽ và nguy hiểm không kém. Để bảo vệ chính mình và những người quan trọng, Venom buộc phải chiến đấu đến hơi thở cuối cùng, với những nguy cơ rủi ro và sự bất ngờ nằm ngoài dự đoán.",
                    "https://iguov8nhvyobj.vcdn.cloud/media/catalog/product/cache/1/image/1800x/71252117777b696995f01934522c402d/6/4/640x396_venom3.jpg",
                    "Kelly Marcel","Tom Hardy, Chiwetel Ejiofor, Juno Temple, Peggy Lu, Rhys Ifans",
                    "Hành động, Phiêu lưu, Giả tưởng, Khoa học viễn tưởng",LocalDate.parse("25/10/2024",formatter),
                    109,"https://www.youtube.com/embed/id1rfr_KZWg",
                    "Tiếng Anh - Phụ đề Tiếng Việt",
                    "T13 - Phim được phổ biến đến người xem từ đủ 13 tuổi trở lên (13+)",1);
            Movie quy = addNewMovie("TEE YOD: QUỶ ĂN TẠNG PHẦN 2","https://iguov8nhvyobj.vcdn.cloud/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/r/s/rsz_ty2-main-poster-printing.jpg",
                    "Cơn ác mộng trở lại với con quỷ đáng sợ truy đuổi không buông tha.",
                    "Một nhóm bạn vô tình đánh thức một linh hồn khát máu khi khám phá ngôi đền bỏ hoang. Những ám ảnh kinh hoàng dần trỗi dậy, đưa họ vào cuộc đấu tranh sinh tồn với con quỷ khát máu, gieo rắc nỗi sợ hãi tột cùng khiến họ không thể trốn thoát.",
                    "https://iguov8nhvyobj.vcdn.cloud/media/catalog/product/cache/1/image/1800x/71252117777b696995f01934522c402d/r/s/rsz_thumb-teaser.jpg",
                    " Taweewat Wantha","Nadech Kugimiya, Denise Jelilcha Kapaun, Mim Rattawadee Wongthong, Junior Kajbhunditt Jaidee, Friend Peerakrit Phacharaboonyakiat, Nutthatcha Jessica Padovan",
                    "Kinh Dị",LocalDate.parse("18/10/2024",formatter),
                    111,"https://www.youtube.com/embed/vHONH3M9RYU",
                    "Tiếng Thái - Phụ đề Tiếng Việt và Tiếng Anh",
                    "T18 - Phim được phổ biến đến người xem từ đủ 18 tuổi trở lên (18+)",1);
            Movie matmaDo = addNewMovie("MẬT MÃ ĐỎ","https://iguov8nhvyobj.vcdn.cloud/media/catalog/product/cache/3/image/1800x/71252117777b696995f01934522c402d/r/s/rsz_redone_insta_vert_main_1638x2048_intl.jpg",
                    "Một mật mã nguy hiểm dẫn lối vào thế giới ngầm đầy rủi ro.",
                    "Khi một nhà mật mã tài năng bị ép buộc giải mã chuỗi ký tự kỳ lạ, cô không ngờ rằng nó sẽ mở ra những bí mật chết người của tổ chức tội phạm nguy hiểm. Cuộc rượt đuổi ly kỳ kéo dài từ thành phố tới sa mạc, nơi cô phải tìm cách sống sót và lật tẩy kẻ thù.",
                    "https://i.ytimg.com/vi/2T_mKyH17mY/maxresdefault.jpg","Jake Kasdan",
                    "Dwayne Johnson; Chris Evans; Lucy Liu",
                    "Hài, Hành Động, Phiêu Lưu",LocalDate.parse("08/11/2024",formatter),
                    125,"https://www.youtube.com/embed/2T_mKyH17mY","Tiếng Anh - Phụ đề Tiếng Việt",
                    "K - Phim được phổ biến đến người xem dưới 13 tuổi và có người bảo hộ đi kèm",1);
            Movie cam = addNewMovie("CÁM","https://iguov8nhvyobj.vcdn.cloud/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/3/0/300wx450h-cam_1_1.jpg",
                    "Câu chuyện cổ tích quen thuộc với góc nhìn mới đầy bất ngờ.",
                    "“Cám” là hành trình của một cô gái bình thường trong thế giới của những phép thuật và sự ganh đua khốc liệt. Câu chuyện tái hiện mối quan hệ chị em, những toan tính, tình yêu, và sự trả giá cho lòng tham. Một góc nhìn mới về câu chuyện cổ tích Việt Nam nổi tiếng.",
                    "https://i.ytimg.com/vi/_8qUFEmPQbc/maxresdefault.jpg",
                    "Trần Hữu Tấn","Quốc Cường, Thúy Diễm, Rima Thanh Vy, Lâm Thanh Mỹ, Hải Nam",
                    "Kinh Dị",LocalDate.parse("20/09/2024",formatter),
                    122,"https://www.youtube.com/embed/_8qUFEmPQbc","Tiếng Việt - Phụ đề Tiếng Anh",
                    "T18 - Phim được phổ biến đến người xem từ đủ 18 tuổi trở lên (18+)",1);
            Movie chuyenTinh = addNewMovie("NGÀY XƯA CÓ MỘT CHUYỆN TÌNH","https://iguov8nhvyobj.vcdn.cloud/media/catalog/product/cache/3/image/1800x/71252117777b696995f01934522c402d/n/x/nxcmct_-_1st_look_poster_-_kc_10.2024_1_.jpg",
                    "Chuyện tình thanh xuân đẹp đẽ nhưng nhiều bi kịch khiến người xem xúc động.",
                    "Tình yêu đầu đời của đôi bạn trẻ lớn lên cùng nhau dường như hoàn hảo cho đến khi sóng gió ập đến, đẩy họ vào những ngã rẽ đau lòng. Tình yêu, định mệnh và sự mất mát đan xen trong một câu chuyện đầy cảm xúc, dẫn dắt khán giả vào từng khoảnh khắc hồi hộp, cảm động.",
                    "https://i.ytimg.com/vi/clisHvIYcKo/maxresdefault.jpg",
                    "Trịnh Đình Lê Minh","Avin Lu, Ngọc Xuân, Đỗ Nhật Hoàng, Thanh Tú, Bảo Tiên, Hạo Khang",
                    "Tình cảm",LocalDate.parse("28/10/2024",formatter),135,
                    "https://www.youtube.com/embed/qaeHlk0OXec","Tiếng Việt - Phụ đề Tiếng Anh",
                    "T16 - Phim được phổ biến đến người xem từ đủ 16 tuổi trở lên (16+)",1);
            Movie robot = addNewMovie("ROBOT HOANG DÃ","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZPtwSjS2kzf04Fbi5AL4y_72WewMb3IV3-A&s"
                    ,"Một tương lai nơi robot tự phát triển ý thức và tự do.",
                    "Trong một thế giới nơi công nghệ đạt tới đỉnh cao, một robot trí tuệ nhân tạo đột ngột phát triển ý thức và quyết định tìm hiểu về thế giới tự nhiên. Sự nổi loạn của nó dẫn đến xung đột với con người, đặt ra câu hỏi về tự do và ý nghĩa của tồn tại trong một thế giới nơi mọi thứ đều có thể kiểm soát."
                    ,"https://i.ytimg.com/vi/NuYnY_P3npY/sddefault.jpg",
                    "Chris Sanders","Lupita Nyong'o, Pedro Pascal, Catherine O’hara, Bill Nighy","Gia đình, Hoạt Hình, Khoa Học Viễn Tưởng, Phiêu Lưu",
                    LocalDate.parse("11/10/2024",formatter),
                    102,"https://www.youtube.com/embed/2l8_FNIBWLM",
                    "Tiếng Anh - Phụ đề Tiếng Việt; Lồng tiếng",
                    "P - Phim được phép phổ biến đến người xem ở mọi độ tuổi.",1);

            // Tạo mới các chi nhánh
            List<Branch> listBranch = branchRepository.findAll();
            if(listBranch.isEmpty()){
                Branch branch1 = new Branch();
                branch1.setName("CINEMA Vincom Thủ Đức");
                branch1.setDiaChi("Tầng 5, TTTM Vincom Thủ Đức, 216 Võ Văn Ngân, Phường Bình Thọ, Quận Thủ Đức");
                branch1.setPhoneNo("1900 6017");
                branch1.setImgURL("https://pasgo.vn/Upload/anh-chi-tiet/vincom-thu-duc--tang-3--so-216-vo-van-ngan--p--binh-tho--q--thu-duc-10850047106765342.webp");
                branch1 = branchRepository.save(branch1);
                Room room1 = new Room();
                room1.setName("Phòng 101");
                room1.setBranch(branch1);
                room1.setCapacity(40);
                room1.setTotalArea(80);
                room1.setImgURL("https://media.bizwebmedia.net/sites/23563/data/Upload/2016/2/di_xem_phim_rap__vi_tri_ngoi_nao_la_tot_nhat__3.jpg");
                Room r1 = roomRepository.save(room1);
                Schedule schedule1 = new Schedule();
                schedule1.setBranch(branch1);
                schedule1.setMovie(robot);
                schedule1.setRoom(r1);
                schedule1.setStartDate(LocalDate.parse("2024-11-20"));
                schedule1.setStartTime(LocalTime.parse("10:15"));
                schedule1.setPrice(70000);
                scheduleRepository.save(schedule1);

                Schedule schedule20 = new Schedule();
                schedule20.setBranch(branch1);
                schedule20.setMovie(cam);
                schedule20.setRoom(r1);
                schedule20.setStartDate(LocalDate.parse("2024-11-20"));
                schedule20.setStartTime(LocalTime.parse("19:15"));
                schedule20.setPrice(70000);
                scheduleRepository.save(schedule20);

                Schedule schedule21 = new Schedule();
                schedule21.setBranch(branch1);
                schedule21.setMovie(matmaDo);
                schedule21.setRoom(r1);
                schedule21.setStartDate(LocalDate.parse("2024-11-20"));
                schedule21.setStartTime(LocalTime.parse("19:15"));
                schedule21.setPrice(70000);
                scheduleRepository.save(schedule21);

                Schedule schedule22 = new Schedule();
                schedule22.setBranch(branch1);
                schedule22.setMovie(quy);
                schedule22.setRoom(r1);
                schedule22.setStartDate(LocalDate.parse("2024-11-20"));
                schedule22.setStartTime(LocalTime.parse("19:15"));
                schedule22.setPrice(70000);
                scheduleRepository.save(schedule22);

                Schedule schedule23 = new Schedule();
                schedule23.setBranch(branch1);
                schedule23.setMovie(aiOan);
                schedule23.setRoom(r1);
                schedule23.setStartDate(LocalDate.parse("2024-11-20"));
                schedule23.setStartTime(LocalTime.parse("19:15"));
                schedule23.setPrice(70000);
                scheduleRepository.save(schedule23);

                Schedule schedule24 = new Schedule();
                schedule24.setBranch(branch1);
                schedule24.setMovie(venom);
                schedule24.setRoom(r1);
                schedule24.setStartDate(LocalDate.parse("2024-11-20"));
                schedule24.setStartTime(LocalTime.parse("19:15"));
                schedule24.setPrice(70000);
                scheduleRepository.save(schedule24);

                Schedule schedule5 = new Schedule();
                schedule5.setBranch(branch1);
                schedule5.setMovie(robot);
                schedule5.setRoom(r1);
                schedule5.setStartDate(LocalDate.parse("2024-11-20"));
                schedule5.setStartTime(LocalTime.parse("13:05"));
                schedule5.setPrice(70000);
                scheduleRepository.save(schedule5);

                Schedule schedule6 = new Schedule();
                schedule6.setBranch(branch1);
                schedule6.setMovie(robot);
                schedule6.setRoom(r1);
                schedule6.setStartDate(LocalDate.parse("2024-11-20"));
                schedule6.setStartTime(LocalTime.parse("14:05"));
                schedule6.setPrice(70000);
                scheduleRepository.save(schedule6);

                Schedule schedule7 = new Schedule();
                schedule7.setBranch(branch1);
                schedule7.setMovie(robot);
                schedule7.setRoom(r1);
                schedule7.setStartDate(LocalDate.parse("2024-11-20"));
                schedule7.setStartTime(LocalTime.parse("16:20"));
                schedule7.setPrice(70000);
                scheduleRepository.save(schedule7);


                Schedule schedule8 = new Schedule();
                schedule8.setBranch(branch1);
                schedule8.setMovie(robot);
                schedule8.setRoom(r1);
                schedule8.setStartDate(LocalDate.parse("2024-11-20"));
                schedule8.setStartTime(LocalTime.parse("16:20"));
                schedule8.setPrice(70000);
                scheduleRepository.save(schedule8);

                Schedule schedule9 = new Schedule();
                schedule9.setBranch(branch1);
                schedule9.setMovie(robot);
                schedule9.setRoom(r1);
                schedule9.setStartDate(LocalDate.parse("2024-11-21"));
                schedule9.setStartTime(LocalTime.parse("16:20"));
                schedule9.setPrice(70000);
                scheduleRepository.save(schedule9);

                Schedule schedule10 = new Schedule();
                schedule10.setBranch(branch1);
                schedule10.setMovie(robot);
                schedule10.setRoom(r1);
                schedule10.setStartDate(LocalDate.parse("2024-11-21"));
                schedule10.setStartTime(LocalTime.parse("19:20"));
                schedule10.setPrice(70000);
                scheduleRepository.save(schedule10);

                Schedule schedule = new Schedule();
                schedule.setBranch(branch1);
                schedule.setMovie(chuyenTinh);
                schedule.setRoom(r1);
                schedule.setStartDate(LocalDate.parse("2024-11-20"));
                schedule.setStartTime(LocalTime.parse("19:15"));
                schedule.setPrice(70000);
                scheduleRepository.save(schedule);

                Room room2 = new Room();
                room2.setName("Phòng 202");
                room2.setBranch(branch1);
                room2.setCapacity(40);
                room2.setTotalArea(80);
                room2.setImgURL("https://media.bizwebmedia.net/sites/23563/data/Upload/2016/2/di_xem_phim_rap__vi_tri_ngoi_nao_la_tot_nhat__3.jpg");
                Room r2 = roomRepository.save(room2);
                Schedule schedule2 = new Schedule();
                schedule2.setBranch(branch1);
                schedule2.setMovie(chuyenTinh);
                schedule2.setRoom(r2);
                schedule2.setStartDate(LocalDate.parse("2024-11-20"));
                schedule2.setStartTime(LocalTime.parse("10:15"));
                schedule2.setPrice(70000);
                scheduleRepository.save(schedule2);

                Schedule schedule77 = new Schedule();
                schedule77.setBranch(branch1);
                schedule77.setMovie(robot);
                schedule77.setRoom(r2);
                schedule77.setStartDate(LocalDate.parse("2024-11-20"));
                schedule77.setStartTime(LocalTime.parse("16:20"));
                schedule77.setPrice(70000);
                scheduleRepository.save(schedule77);


                Room room3 = new Room();
                room3.setName("Phòng 303");
                room3.setBranch(branch1);
                room3.setCapacity(40);
                room3.setTotalArea(80);
                room3.setImgURL("https://media.bizwebmedia.net/sites/23563/data/Upload/2016/2/di_xem_phim_rap__vi_tri_ngoi_nao_la_tot_nhat__3.jpg");
                Room r3 = roomRepository.save(room3);
                Schedule schedule3 = new Schedule();
                schedule3.setBranch(branch1);
                schedule3.setMovie(venom);
                schedule3.setRoom(r3);
                schedule3.setStartDate(LocalDate.parse("2024-11-20"));
                schedule3.setStartTime(LocalTime.parse("10:15"));
                schedule3.setPrice(70000);
                scheduleRepository.save(schedule3);

                Schedule schedule88 = new Schedule();
                schedule88.setBranch(branch1);
                schedule88.setMovie(robot);
                schedule88.setRoom(r3);
                schedule88.setStartDate(LocalDate.parse("2024-11-20"));
                schedule88.setStartTime(LocalTime.parse("16:20"));
                schedule88.setPrice(70000);
                scheduleRepository.save(schedule88);

                Room room4 = new Room();
                room4.setName("Phòng 404");
                room4.setBranch(branch1);
                room4.setCapacity(40);
                room4.setTotalArea(80);
                room4.setImgURL("https://media.bizwebmedia.net/sites/23563/data/Upload/2016/2/di_xem_phim_rap__vi_tri_ngoi_nao_la_tot_nhat__3.jpg");
                Room r4 = roomRepository.save(room4);

                Schedule schedule99 = new Schedule();
                schedule99.setBranch(branch1);
                schedule99.setMovie(robot);
                schedule99.setRoom(r4);
                schedule99.setStartDate(LocalDate.parse("2024-11-20"));
                schedule99.setStartTime(LocalTime.parse("16:20"));
                schedule99.setPrice(70000);
                scheduleRepository.save(schedule99);

                Branch branch2 = new Branch();
                branch2.setName("CINEMA Vincom Center Landmark 81");
                branch2.setDiaChi("Tầng B1 , TTTM Vincom Center Landmark 81, 772 Điện Biên Phủ, P.22, Q. Bình Thạnh, HCM");
                branch2.setPhoneNo("1900 6017");
                branch2.setImgURL("https://pasgo.vn/Upload/anh-chi-tiet/vincom-center-landmard-81--lo-b1-04-05--so-722-dien-bien-phu--p--22--q--binh-thanh-107941310670141.webp");
                branch2 = branchRepository.save(branch2);
                room1.setBranch(branch2);
                room2.setBranch(branch2);
                room3.setBranch(branch2);
                Room r5 = roomRepository.save(room1);
                Room r6 = roomRepository.save(room2);
                Room r7 = roomRepository.save(room3);
                Schedule schedule11 = new Schedule();
                schedule11.setBranch(branch2);
                schedule11.setMovie(robot);
                schedule11.setRoom(r5);
                schedule11.setStartDate(LocalDate.parse("2024-11-20"));
                schedule11.setStartTime(LocalTime.parse("10:15"));
                schedule11.setPrice(70000);
                scheduleRepository.save(schedule11);


                Branch branch3 = new Branch();
                branch3.setName("CINEMA Aeon Tân Phú");
                branch3.setDiaChi("Lầu 3, Aeon Mall 30 Bờ Bao Tân Thắng, P. Sơn Kỳ Quận Tân Phú TP. Hồ Chí Minh");
                branch3.setPhoneNo("1900 6017");
                branch3.setImgURL("https://pasgo.vn/Upload/anh-chi-tiet/tttm-aeon-mall-tan-phu--tang-3--so-30-bo-bao-tan-thang--p--son-ky--q--tan-phu-10801579106617655.webp");
                branch3 = branchRepository.save(branch3);
                room1.setBranch(branch3);
                room2.setBranch(branch3);
                room3.setBranch(branch3);
                room4.setBranch(branch3);
                Room r8= roomRepository.save(room1);
                Room r9 = roomRepository.save(room2);
                Room r10 = roomRepository.save(room3);
                Room r11 = roomRepository.save(room4);
                Schedule schedule12 = new Schedule();
                schedule12.setBranch(branch3);
                schedule12.setMovie(robot);
                schedule12.setRoom(r8);
                schedule12.setStartDate(LocalDate.parse("2024-11-20"));
                schedule12.setStartTime(LocalTime.parse("10:15"));
                schedule12.setPrice(70000);
                scheduleRepository.save(schedule12);
                Schedule schedule13 = new Schedule();
                schedule13.setBranch(branch3);
                schedule13.setMovie(chuyenTinh);
                schedule13.setRoom(r9);
                schedule13.setStartDate(LocalDate.parse("2024-11-20"));
                schedule13.setStartTime(LocalTime.parse("22:15"));
                schedule13.setPrice(70000);
                scheduleRepository.save(schedule12);

                Branch branch4 = new Branch();
                branch4.setName("CINEMA Vincom Đồng Khởi");
                branch4.setDiaChi("Tầng 3, TTTM Vincom Center Đồng Khởi, 72 Lê Thánh Tôn & 45A Lý Tự Trọng, Quận 1, TP.HCM.");
                branch4.setPhoneNo("1900 6017");
                branch4.setImgURL("https://pasgo.vn/Upload/anh-chi-tiet/tang-b3--b3---06--vincom-dong-khoi--72-le-thanh-ton--p--ben-nghe--q--1-10778207106701822.webp");
                branch4 = branchRepository.save(branch4);
                room1.setBranch(branch4);
                room2.setBranch(branch4);
                room3.setBranch(branch4);
                room4.setBranch(branch4);
                Room r12 = roomRepository.save(room1);
                roomRepository.save(room2);
                roomRepository.save(room3);
                roomRepository.save(room4);

                Schedule schedule14 = new Schedule();
                schedule14.setBranch(branch4);
                schedule14.setMovie(robot);
                schedule14.setRoom(r12);
                schedule14.setStartDate(LocalDate.parse("2024-11-20"));
                schedule14.setStartTime(LocalTime.parse("10:15"));
                schedule14.setPrice(70000);
                scheduleRepository.save(schedule14);
            }
        }




    }

    public Movie addNewMovie(
            String name,
            String smallImageURl,
            String shortDescription,
            String longDescription,
            String largeImageURL,
            String director,
            String actors,
            String categories,
            LocalDate releaseDate,
            int duration,
            String trailerURL,
            String language,
            String rated,
            int isShowing) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setSmallImageURl(smallImageURl);
        movie.setShortDescription(shortDescription);
        movie.setLongDescription(longDescription);
        movie.setLargeImageURL(largeImageURL);
        movie.setDirector(director);
        movie.setActors(actors);
        movie.setCategories(categories);
        movie.setReleaseDate(releaseDate);
        movie.setDuration(duration);
        movie.setTrailerURL(trailerURL);
        movie.setLanguage(language);
        movie.setRated(rated);
        movie.setIsShowing(isShowing);
        return movieRepository.save(movie);
    }


    public static void main(String[] args) {
        SpringApplication.run(CinemaBackEndApplication.class, args);
    }

}
