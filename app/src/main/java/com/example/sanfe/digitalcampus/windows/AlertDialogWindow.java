package com.example.sanfe.digitalcampus.windows;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.sanfe.digitalcampus.activities.StudentManagerActivity;
import com.example.sanfe.digitalcampus.activities.SubjectManagerActivity;

/**
 * Crea un missatge d'error amb el missatge especificat
 */
public class AlertDialogWindow {

    public static void errorMessage (Context context, String title, String text) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(text).setCancelable(false)
                .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void confirmationMessage (Context context, String title, String text, final int position) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(text)
                .setCancelable(false).setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                SubjectManagerActivity.eliminaAsignatura(position);
            }
        })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void confirmationMessage2 (Context context, String title, String text, final int position) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(text)
                .setCancelable(false).setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            public void onClick (DialogInterface dialog, int id) {
                StudentManagerActivity.eliminaAlumno(position);
            }
        })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
