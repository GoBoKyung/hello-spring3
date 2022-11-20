package hello.hellospring.repository;

import hello.hellospring.domain.member;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class jdbcMemberRepository implements MemberRepository{

    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public member save(member memer, int parameterIndex) {
        String sql = "insert into member(name) values(?)";
        Connection conn = null;
        PreparedStatement pstmt  = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(parameterIndex: 1, member.getName());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if(rs.next()) {
                member.setId(rs.getLong(columnIndex: 1));
            } else {
                throw new SQLException("id 조회 실패");

            }
            return member;
        }catch (Exception e) {
            throw new IllegalStateException(e);
        }finally {
            close(conn, pstmt, rs);
        }

    }

    @Override
    public Optional<member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<member> findAll() {
        return null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
