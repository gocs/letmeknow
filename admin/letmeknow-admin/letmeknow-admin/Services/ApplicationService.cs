using letmeknow_admin.Models;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace letmeknow_admin.Services
{
    class ApplicationService
    {
        private static string attr = GeneralSetting.host + "serves/";

        public static ObservableCollection<Application> getAllApplications()
        {
            string JsonString = HttpHelper.Get(attr + "allServes");
            return new ObservableCollection<Application>(JsonHelper.DeserializeJsonToList<Application>(JsonString));
        }

        public static void approveApplication(Application application)
        {
            //Dictionary<string, string> parameters = new Dictionary<string, string>();
            //parameters["applicationId"] = application.applicationId.ToString();
            HttpHelper.Put(attr + "judgeServe/" + application.id + "/1");
        }

        public static void rejectApplication(Application application)
        {
            //Dictionary<string, string> parameters = new Dictionary<string, string>();
            //parameters["applicationId"] = application.applicationId.ToString();
            //HttpHelper.Post(GeneralSetting.host + "denyApplication", parameters);
            HttpHelper.Put(attr + "judgeServe/" + application.id + "/0");
        }
    }
}
