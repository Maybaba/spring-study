package com.study.springstudy.springmvc.chap03.repository;

import com.study.springstudy.springmvc.chap03.ScoreRepository;
import com.study.springstudy.springmvc.chap03.entity.Score;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// 구체적인 함수 설명해. 난 마리아 디비에 설정할거야.
@Component //이제 스프링이 관리할겨.
@Repository //스프링이 관리 -> 저장소를 관리
public class ScoreJdbcRepository implements ScoreRepository {

    private String url = "jdbc:mariadb://localhost:3306/spring5";
    private String username = "root";
    private String password = "1234";

    public ScoreJdbcRepository() {
        try{
            Class.forName("org.mariadb.jdbc.Driver"); //db마다 다르므로 검색
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean save(Score score) {
            try(Connection conn = connect()){
                String sql = "INSERT INTO tbl_score" +
                        "(stu_name, kor, eng, math, total, average, grade)" +
                        "VALUES(?,?,?,?,?,?,?)";

                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, score.getStuName());
                pstmt.setInt(2, score.getKor());
                pstmt.setInt(3, score.getEng());
                pstmt.setInt(4, score.getMath());
                pstmt.setInt(5, score.getTotal());
                pstmt.setDouble(6, score.getAverage());
                pstmt.setString(7, score.getGrade().toString());

                int result = pstmt.executeUpdate();
                if(result == 1) return true;
            }catch (Exception e){
                e.printStackTrace();
            }
            return false;
        }

    @Override
    public List<Score> findAll() {

        List<Score> scoreList = new ArrayList<>();

        try (Connection conn = connect()) {

            String sql = "SELECT * FROM tbl_score";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Score s = new Score(rs);
                scoreList.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreList;
    }


    @Override
    public Score findOne(long stuNum) {
        //stuNum으로 행 찾기
        try (Connection conn = connect()) {

            String sql = "SELECT * FROM tbl_score WHERE stu_num = ? ";
            //+stuNum; //pk로 where 절 걸면 1줄이거나 0줄. 당연한 얘기임 하나 밖에 없는 값이니까.

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, stuNum); //setLong : SQL 쿼리의 첫 번째 파라미터('?')에 stuNum 변수의 값을 설정

            ResultSet rs = pstmt.executeQuery();
            // 찾은 값 Score 객체추가
            if (rs.next()) { //rs 값 입력
                return new Score(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int[] findRankbyOne(long stuNum) {
        //stuNum으로 행 찾기
        try(Connection conn = connect()) {
           String sql = "SELECT A.stu_num, A.rank, A.cnt" +
                    " FROM (SELECT *, " +
                    "           RANK() OVER (ORDER BY average DESC) AS rank, " +
                    "           COUNT(*) OVER() AS cnt" +
                    "       FROM tbl_score) A " +
                    "WHERE A.stu_num = ?";;

           PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, stuNum); //setLong : SQL 쿼리의 첫 번째 파라미터('?')에 stuNum 변수의 값을 설정

            //구해지는 값에 따라 new int [] 출력
            ResultSet rs = pstmt.executeQuery();
                    //그렇게 반복문을 돌려서..... stunum 마다의 값을 얻는다.
            if(rs.next()) {
                return new int[] {
                        rs.getInt("rank"),
                        rs.getInt("cnt")
                };
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Score> Delete(long stuNum) {
        List<Score> scoreList = new ArrayList<>();

        try (Connection conn = connect()) {

            String sql = "SELECT * FROM tbl_score where 삭제버튼을 누른 행의 스튜넘 리턴하기";

            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Score s = new Score(rs);
                scoreList.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreList; //-> 해당 열 삭제한 후 값을 반환해서 리다이렉트 list로 걸기
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}


