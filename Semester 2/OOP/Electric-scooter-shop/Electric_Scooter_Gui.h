#include <QWidget>
#include <QTableWidget>
#include <QPushButton>
#include "Controller.h"
using namespace Controller;

class ScooterGUI : public QWidget {
Q_OBJECT

public:
    explicit ScooterGUI(QWidget* parent = nullptr);

private slots:
    void loadInMemory();
    void loadCSV();
    void viewDetails();

private:
    QTableWidget* tableWidget;
    QPushButton* loadInMemoryButton;
    QPushButton* loadCSVButton;
    QPushButton* detailsButton;

    Electric_scooter_Controller scooterController;

    void createTable();
    void updateTable();
};

