package org.example.dao;

import org.example.entity.Member;
import org.example.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MemberDAOImplementation implements MemberDao{
    @Override
    public boolean insertMember(Member m) {
        String q = "Insert into member (userId,name,email) values (?,?,?)";

        try(Connection con = DBConnectionUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(q);){

            ps.setInt(1,m.getUserId());
            ps.setString(2,m.getName());
            ps.setString(3,m.getEmail());

            return ps.executeUpdate() > 0;

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }
}
