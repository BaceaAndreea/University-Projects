#include <QDialog>
#include "Domain.h"

class ScooterDetailsDialog : public QDialog {
Q_OBJECT

public:
    ScooterDetailsDialog(const Domain::Electric_scooter* scooter, QWidget* parent = nullptr);

private:
    void createLayout(Domain::Electric_scooter *scooter);
};

