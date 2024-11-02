/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.saam.teste.pratico.util;

import com.mycompany.saam.teste.pratico.model.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jonat
 */
public class SqlGeneration {

    public static String insertSql(Model table) {
        return insertSql(table, table.getFieldNames());
    }

    public static String insertSql(Model table, List<String> campos) {
        StringBuilder sqlInsert = new StringBuilder("INSERT INTO ")
                .append(table.getTableName())
                .append(" (")
                .append(String.join(", ", campos))
                .append(") VALUES (");
        List<String> placeHolders = new ArrayList<>();
        campos.forEach(campo -> placeHolders.add("?"));
        sqlInsert.append(String.join(", ", placeHolders))
                 .append(");");
        return sqlInsert.toString();
    }

    public static String selectSql(Model table) {
        return selectSql(table, table.getFieldNames(), null);
    }

    public static String selectSql(Model table, String where) {
        return selectSql(table, table.getFieldNames(), where);
    }

    public static String selectSql(Model table, List<String> campos, String where) {
        StringBuilder sqlSelect = new StringBuilder("SELECT ")
                .append(String.join(", ", campos))
                .append(" FROM ")
                .append(table.getTableName())
                .append(" ");
        if (where != null && !"".equals(where.trim())) {
            sqlSelect.append(where);
        }
        sqlSelect.append(";");
        return sqlSelect.toString();
    }

    public static String updateSql(Model table) {
        return updateSql(table, table.getFieldNames());
    }

    public static String updateSql(Model table, List<String> campos) {
        StringBuilder sqlUpdate = new StringBuilder("UPDATE ")
                .append(table.getTableName())
                .append(" SET ");
        List<String> setValues = new ArrayList<>();
        campos.forEach(campo -> setValues.add(campo + " = ?"));
        sqlUpdate.append(String.join(", ", setValues))
                 .append(" WHERE ")
                 .append(table.getId())
                 .append(" = ?;");
        return sqlUpdate.toString();
    }

    public static String deleteSql(Model table) {
        StringBuilder sqlDelete = new StringBuilder("DELETE FROM ")
                .append(table.getTableName())
                .append(" WHERE ")
                .append(table.getId())
                .append(" = ?;");
        return sqlDelete.toString();
    }

}
