package net.sunil.bean;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;


import net.sunil.modal.InvoiceType;


public class UserDto {

  @Enumerated(EnumType.STRING)
  private InvoiceType invoiceType;
  @NotEmpty
  private String invoiceNumber;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private Date invoiceDate;
@Override
public String toString() {
	return "UserDto [invoiceType=" + invoiceType + ", invoiceNumber=" + invoiceNumber + ", invoiceDate=" + invoiceDate
			+ "]";
}
public InvoiceType getInvoiceType() {
	return invoiceType;
}
public void setInvoiceType(InvoiceType invoiceType) {
	this.invoiceType = invoiceType;
}
public String getInvoiceNumber() {
	return invoiceNumber;
}
public void setInvoiceNumber(String invoiceNumber) {
	this.invoiceNumber = invoiceNumber;
}
public Date getInvoiceDate() {
	return invoiceDate;
}
public void setInvoiceDate(Date invoiceDate) {
	this.invoiceDate = invoiceDate;
}

}
