#include "Electric_Scooter_details_dialog.h"
#include <QVBoxLayout>
#include <QLabel>

ScooterDetailsDialog::ScooterDetailsDialog(const Domain::Electric_scooter* scooter, QWidget* parent)
        : QDialog(parent) {
    setWindowTitle("Scooter Details");
    setFixedSize(400, 300);

    createLayout(const_cast<Domain::Electric_scooter *>(scooter));
}

void ScooterDetailsDialog::createLayout(Domain::Electric_scooter *scooter) {
    QVBoxLayout* layout = new QVBoxLayout(this);

    QLabel* identifierLabel = new QLabel("Identifier: " + QString::fromStdString(scooter->get_identifier()));
    QLabel* modelLabel = new QLabel("Model: " + QString::fromStdString(scooter->get_model()));
    QLabel* dateLabel = new QLabel("Date: " + QString::fromStdString(scooter->get_commissioning_date()));
    QLabel* kilometerLabel = new QLabel("Kilometers: " + QString::number(scooter->get_kilometer()));
    QLabel* locationLabel = new QLabel("Location: " + QString::fromStdString(scooter->get_location()));
    QLabel* stateLabel = new QLabel("Condition: " + QString::fromStdString(scooter->get_condition()));

    layout->addWidget(identifierLabel);
    layout->addWidget(modelLabel);
    layout->addWidget(dateLabel);
    layout->addWidget(kilometerLabel);
    layout->addWidget(locationLabel);
    layout->addWidget(stateLabel);

    setLayout(layout);
}