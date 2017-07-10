using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json.Linq;
using System.Threading.Tasks;

namespace letmeknow_admin.Services
{
    class AccountService
    {
        public enum LoginResult { SUCCESS = 2, WRONG = 0, NOAUTH = 1 };

        public static LoginResult login(string username, string password)
        {
            Dictionary<string, string> parameters = new Dictionary<string, string>();
            parameters["username"] = username;
            parameters["password"] = password;
            string JsonString = HttpHelper.Post(GeneralSetting.host + "login", parameters);
            var result = (LoginResult)int.Parse(JObject.Parse(JsonString)["message"].ToString());
            return result;
        }

        public static Boolean changePassword(string oldPwd, string newPwd)
        {
            return true;
        }
    }
}
