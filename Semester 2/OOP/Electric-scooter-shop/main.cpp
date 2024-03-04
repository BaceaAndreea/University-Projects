#include <QApplication>
#include "Electric_Scooter_Gui.h"

int main(int argc, char *argv[]){
    QApplication app(argc, argv);
    ScooterGUI scooterGUI;
    scooterGUI.show();

    return app.exec();
}

/*
#include "UI.h"
#include "Tests.h"
#include <memory>

using namespace UI;

int main() {

    shared_ptr<Electric_scooter_Controller> Electric_Scooter_Controller = make_shared<Electric_scooter_Controller>();
    Electric_Scooter_Controller->InMemory();
    Test_All();

    Electric_scooter_UI User_Interface;
    User_Interface.RUN();

    return 0;
}
*/