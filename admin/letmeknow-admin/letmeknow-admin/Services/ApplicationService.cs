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
        public static ObservableCollection<Application> getAllApplications()
        {
            string JsonString = HttpHelper.Get(GeneralSetting.host + "allApplications", string.Empty);
            return JsonHelper.DeserializeJsonToObject<Dictionary<string, ObservableCollection<Application>>>(JsonString)["applications"];
        }

        public static void approveApplication(Application application)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["applicationId"] = application.applicationId.ToString();
            HttpHelper.Post(GeneralSetting.host + "passApplication", parameters);
        }

        public static void rejectApplication(Application application)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["applicationId"] = application.applicationId.ToString();
            HttpHelper.Post(GeneralSetting.host + "denyApplication", parameters);
        }
    }
}
