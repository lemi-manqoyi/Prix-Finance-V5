// `PrixFinanceV3/app/src/main/java/com/lemi/prix_finance_v3/AddTransaction.kt`
package com.lemi.prix_finance_v3

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddTransaction : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        val edtAmount: EditText = findViewById(R.id.edtTransactionAmount)
        val edtName: EditText = findViewById(R.id.edtTransactionName)
        val spinnerCategory: Spinner = findViewById(R.id.TransactionCategory)
        val btnDate: Button = findViewById(R.id.btnDate)
        val btnSave: Button = findViewById(R.id.btnSave)
        val btnCancel: Button = findViewById(R.id.btnCancel)
        val txtDate: TextView = findViewById(R.id.txtDate)

        val categories = arrayOf(
            "Housing", "Transportation", "Food", "Utilities", "Clothing", "Medical", "Insurance",
            "Groceries", "Personal", "Debt/Payments", "Retirement", "Education", "Savings",
            "Entertainment", "Other"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        spinnerCategory.adapter = adapter

        var selectedDate = ""

        btnDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(this, { _, year, month, day ->
                val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                calendar.set(year, month, day)
                selectedDate = formattedDate.format(calendar.time)
                txtDate.text = selectedDate
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        btnSave.setOnClickListener {
            val amount = edtAmount.text.toString()
            val name = edtName.text.toString()
            val category = spinnerCategory.selectedItem.toString()

            if (amount.isBlank() || name.isBlank() || selectedDate.isBlank()) {
                Toast.makeText(this, "Please ensure that all fields have been entered", Toast.LENGTH_SHORT).show()
            } else {
                val resultIntent = Intent()
                resultIntent.putExtra("transaction_name", name)
                resultIntent.putExtra("transaction_amount", "ZAR $amount")
                resultIntent.putExtra("transaction_category", category)
                resultIntent.putExtra("transaction_date", selectedDate)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }

        btnCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}