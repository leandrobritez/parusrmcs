/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.parusrmcs.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import py.una.pol.par.commons.entity.Entity;
import py.una.pol.par.commons.util.DBUtils;
import py.una.pol.par.parusrmcs.model.entity.User;

/**
 *
 * @author mauricio
 */
public class InMemUserRepository implements UserRepository<User, Integer> {

    private final ArrayList<User> users = new ArrayList<>();

    @Override
    public void add(User entity) {
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("INSERT INTO \"USUARIOS\" (\"ID\",\"NOMBRE\",\"APELLIDO\",\"EMAIL\",\"LOGIN_NAME\",\"PASSWD\",\"TIPO_CLIENTE\") values (?,?,?,?,?,?,?)");
            pstmt.setInt(1, entity.getId());
            pstmt.setString(2, entity.getName());
            pstmt.setString(3, entity.getLastname());
            pstmt.setString(4, entity.getEmail());
            pstmt.setString(5, entity.getLoginName());
            pstmt.setString(6, entity.getPasswd());
            pstmt.setInt(7, entity.getClientType());

            pstmt.execute();
            pstmt.close();
            DBUtils.closeConnection(c);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {

            }
        }
    }

    @Override
    public void remove(Integer id) {
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("DELETE FROM \"USUARIOS\" WHERE \"ID\" = ?");

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {

            }
        }
    }

    @Override
    public void update(User entity) {
        Connection c = null;
        PreparedStatement pstmt = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("UPDATE \"USUARIOS\" SET \"NOMBRE\" = ? ,\"APELLIDO\"= ? ,\"EMAIL\"= ? ,\"LOGIN_NAME\"= ? ,\"PASSWD\"= ? ,\"TIPO_CLIENTE\"= ?  WHERE \"ID\"= ? ");

            
            pstmt.setString(1, entity.getName());
            pstmt.setString(2, entity.getLastname());
            pstmt.setString(3, entity.getEmail());
            pstmt.setString(4, entity.getLoginName());
            pstmt.setString(5, entity.getPasswd());
            pstmt.setInt(6, entity.getClientType());
            pstmt.setInt(7, entity.getId());
            
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {

            }
        }
    }

    @Override
    public boolean contains(Integer id) {
        Entity retValue = null;

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM \"USUARIOS\" WHERE \"ID\" = ? ");

            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new User(rs.getInt("ID"), rs.getString("NOMBRE"), rs.getString("APELLIDO"), rs.getString("EMAIL"), rs.getString("LOGIN_NAME"), rs.getString("PASSWD"), rs.getInt("TIPO_CLIENTE"));
            } else {
                retValue = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {

            }
        }

        if (retValue == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Entity get(Integer id) {
        Entity retValue = null;

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM \"USUARIOS\" WHERE \"ID\" = ?");

            pstmt.setInt(1, id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new User(rs.getInt("ID"), rs.getString("NOMBRE"), rs.getString("APELLIDO"), rs.getString("EMAIL"), rs.getString("LOGIN_NAME"), rs.getString("PASSWD"), rs.getInt("TIPO_CLIENTE"));
            } else {
                retValue = new User(null, null, null, null, null, null, 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {

            }
        }

        return retValue;
    }

    @Override
    public Collection<User> getAll() {
        Collection<User> retValue = new ArrayList();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM \"USUARIOS\"");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                retValue.add(new User(rs.getInt("ID"), rs.getString("NOMBRE"), rs.getString("APELLIDO"), rs.getString("EMAIL"), rs.getString("LOGIN_NAME"), rs.getString("PASSWD"), rs.getInt("TIPO_CLIENTE")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {

            }
        }

        return retValue;
    }

    @Override
    public boolean containsNameLastname(String name, String lastname) {
        Entity retValue = null;

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM \"USUARIOS\" WHERE \"NOMBRE\" = ? AND \"APELLIDO\" = ? ");

            pstmt.setString(1, name);
            pstmt.setString(2, lastname);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new User(rs.getInt("ID"), rs.getString("NOMBRE"), rs.getString("APELLIDO"), rs.getString("EMAIL"), rs.getString("LOGIN_NAME"), rs.getString("PASSWD"), rs.getInt("TIPO_CLIENTE"));
            } else {
                retValue = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {

            }
        }

        if (retValue == null) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public Collection<User> findByNameLastname(String name, String lastname
    ) {
        Collection<User> retValues = new ArrayList<>();

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM \"USUARIOS\" WHERE \"ID\" = ?");

            pstmt.setString(1, name);
            pstmt.setString(2, lastname);
            rs = pstmt.executeQuery();

            
            if (rs.next()) {
                retValues.add(new User(rs.getInt("ID"), rs.getString("NOMBRE"), rs.getString("APELLIDO"), rs.getString("EMAIL"), rs.getString("LOGIN_NAME"), rs.getString("PASSWD"), rs.getInt("TIPO_CLIENTE")));
            } else {
                retValues.add(new User(null, null, null, null, null, null, 0));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {

            }
        }

        return retValues;
    }

    
    @Override
    public boolean containsLoginPasswd(String loginName, String passwd) {
        Entity retValue = null;

        Connection c = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            c = DBUtils.getConnection();
            pstmt = c.prepareStatement("SELECT * FROM \"USUARIOS\" WHERE \"LOGIN_NAME\" = ? AND \"PASSWD\" = ? ");

            pstmt.setString(1, loginName);
            pstmt.setString(2, passwd);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                retValue = new User(rs.getInt("ID"), rs.getString("NOMBRE"), rs.getString("APELLIDO"), rs.getString("EMAIL"), rs.getString("LOGIN_NAME"), rs.getString("PASSWD"), rs.getInt("TIPO_CLIENTE"));
            } else {
                retValue = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                DBUtils.closeConnection(c);
            } catch (SQLException ex) {

            }
        }

        if (retValue == null) {
            return false;
        } else {
            return true;
        }

    }
}
