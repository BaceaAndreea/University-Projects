#include "Controller.h"
#include <memory>

using namespace Controller;
using namespace std;

namespace UI {

    class Electric_scooter_UI{

    public:

        Electric_scooter_UI();
        void RUN();

    private:

        shared_ptr <Electric_scooter_Controller> scooter_controller;

        static void Type();

        static void Menu();

        static void Manager_menu();

        static void User_menu();

        void Manager();

        void User();

        void Add();

        void Delete();

        void Edit();

        void Search_by_location();

        void Filter_by_commissioning_date();

        void Filter_by_kilometer();

        void Sort_by_commissioning_date();

        void Modify_condition();

        void Reserve_scooter();

        void See_reserved_scooters();

    };

}
