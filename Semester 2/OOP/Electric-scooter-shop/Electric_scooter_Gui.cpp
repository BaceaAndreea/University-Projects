#include "Electric_Scooter_Gui.h"
#include "Domain.h"
#include "Electric_Scooter_details_dialog.h"
#include <QVBoxLayout>
#include <QFileDialog>
#include <QMessageBox>
using namespace Domain;

ScooterGUI::ScooterGUI(QWidget* parent) : QWidget(parent) {
    createTable();

    loadInMemoryButton = new QPushButton("Load (In-Memory)", this);
    loadCSVButton = new QPushButton("Load (CSV)", this);
    detailsButton = new QPushButton("Scooter Details", this);

    QVBoxLayout* layout = new QVBoxLayout(this);
    layout->addWidget(tableWidget);
    layout->addWidget(loadInMemoryButton);
    layout->addWidget(loadCSVButton);
    layout->addWidget(detailsButton);

    setLayout(layout);

    connect(loadInMemoryButton, &QPushButton::clicked, this, &ScooterGUI::loadInMemory);
    connect(loadCSVButton, &QPushButton::clicked, this, &ScooterGUI::loadCSV);
    connect(detailsButton, &QPushButton::clicked, this, &ScooterGUI::viewDetails);

}

void ScooterGUI::createTable() {
    tableWidget = new QTableWidget(this);
    tableWidget->setColumnCount(6);
    tableWidget->setHorizontalHeaderLabels(QStringList() << "Identifier" << "Model" << "Date"
                                                         << "Kilometers" << "Location" << "Condition");
    tableWidget->setSortingEnabled(true);
}

void ScooterGUI::updateTable() {
    tableWidget->clearContents();

    const std::vector<Electric_scooter>& scooters = scooterController.get_all();
    int rowCount = static_cast<int>(scooters.size());
    tableWidget->setRowCount(rowCount);

    for (int row = 0; row < rowCount; ++row) {
        Electric_scooter scooter = scooters[row];

        QTableWidgetItem* identifierItem = new QTableWidgetItem(scooter.get_identifier().c_str());
        QTableWidgetItem* modelItem = new QTableWidgetItem(scooter.get_model().c_str());
        QTableWidgetItem* dateItem = new QTableWidgetItem(scooter.get_commissioning_date().c_str());
        QTableWidgetItem* kilometerItem = new QTableWidgetItem(QString::number(scooter.get_kilometer()));
        QTableWidgetItem* locationItem = new QTableWidgetItem(scooter.get_location().c_str());
        QTableWidgetItem* stateItem = new QTableWidgetItem(scooter.get_condition().c_str());

        tableWidget->setItem(row, 0, identifierItem);
        tableWidget->setItem(row, 1, modelItem);
        tableWidget->setItem(row, 2, dateItem);
        tableWidget->setItem(row, 3, kilometerItem);
        tableWidget->setItem(row, 4, locationItem);
        tableWidget->setItem(row, 5, stateItem);
    }
}

void ScooterGUI::loadInMemory() {
    scooterController.InMemory();
    scooterController.add_InMemory();
    updateTable();
}

void ScooterGUI::loadCSV() {
    scooterController.CSV();
    scooterController.add_CSV();
    updateTable();
}


void ScooterGUI::viewDetails() {
    int selectedRow = tableWidget->currentRow();
    if (selectedRow >= 0) {
        QString identifier = tableWidget->item(selectedRow, 0)->text();
        const Electric_scooter* scooter = nullptr;

        for (Electric_scooter s : scooterController.get_all()) {
            if (s.get_identifier() == identifier.toStdString()) {
                scooter = &s;
                break;
            }
        }

        if (scooter != nullptr) {

            ScooterDetailsDialog* dialog = new ScooterDetailsDialog(scooter, this);
            dialog->exec();

        } else {
            QMessageBox::information(this, "Scooter Details", "Scooter not found.");
        }
    }
}