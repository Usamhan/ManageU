package com.samhan.manageu_db;

public class expenseModel {
   public  String amount,note;

    public expenseModel() {

    }

    public expenseModel(String amount, String note) {
        this.amount = amount;
        this.note = note;
        //this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
