package com.nowak.demo.wizards.company

import com.nowak.demo.controllers.InvoiceController
import com.nowak.demo.models.customers.CompanyModel
import com.nowak.demo.models.invoices.CompanyInvoiceModel
import com.nowak.demo.models.invoices.PaymentMethod
import com.nowak.demo.models.items.Item
import com.nowak.demo.models.items.ItemCategory
import com.nowak.demo.models.items.ItemModel
import javafx.collections.FXCollections
import tornadofx.*

class InvoiceDetailsWizardView : View("Invoice informations") {

    private val companyModel: CompanyModel by inject()
    private val companyInvoiceModel: CompanyInvoiceModel by inject()
    private val itemModel: ItemModel by inject()
    private val invoiceController: InvoiceController by inject()
    private val itemsList = FXCollections.observableArrayList<Item>()

    override fun onSave() {
        invoiceController.addNewCompanyInvoice(CompanyInvoiceModel
                .convertCompanyModelToDto(companyInvoiceModel, companyModel), itemsList)
    }

    override val root = vbox {
        style {
            stylesheets.add("styles.css")
        }
        label("Item details")
        form {
            fieldset {
                field("Description") {
                    textfield(itemModel.description) {
                        style { id = "text-field" }
                        required()
                    }
                }
                field("Cost") {
                    textfield(itemModel.cost) {
                        style { id = "text-field" }
                        required()
                    }
                }
                field("Quantity") {
                    textfield(itemModel.quantity) {
                        style { id = "text-field" }
                        required()
                    }
                }
                field("VAT") {
                    textfield(itemModel.vat) {
                        style { id = "text-field" }
                        required()
                    }
                }
                field("Category") {
                    combobox(itemModel.category) {
                        items = FXCollections.observableList(ItemCategory.values().toList())
                        style { id = "text-field" }
                        required()
                    }
                }
                button("Add Item") {
                    id = "dashboard-button"
                    action {
                        itemsList.add(ItemModel.convertItemModelToDto(itemModel))
                    }
                }
            }
        }
        tableview<Item> {
            items = itemsList
            columnResizePolicy = SmartResize.POLICY
            column("Description", Item::description)
            column("Cost", Item::cost)
            column("Quantity", Item::quantity)
            column("Category", Item::category)
            column("VAT", Item::vat)
        }
        label("Invoice details")
        form {

            style { stylesheets.add("styles.css") }
            fieldset {
                field("Date of issue") {
                    datepicker(companyInvoiceModel.dateOfIssue) {
                        style { id = "text-field" }
                        required()
                    }
                }
                field("Amount") {
                    textfield(companyInvoiceModel.amount) {
                        style { id = "text-field" }
                        required()
                    }
                }
                field("PaymentMethod") {
                    combobox(companyInvoiceModel.paymentMethod) {
                        items = FXCollections.observableArrayList(PaymentMethod.values().toList())
                        style { id = "text-field" }
                        required()
                    }
                }
            }
        }

    }
}

