using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Text;
using Newtonsoft.Json.Linq;
using System.Threading.Tasks;
using System.Windows;

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
            HttpStatusCode code;
            
            //HttpHelper.Post(GeneralSetting.host + "tokens", parameters, out code);
            try
            {
                string JsonString = HttpHelper.Post(GeneralSetting.host + "tokens", parameters, out code);
                string token = JObject.Parse(JsonString)["authentication"].ToString();
                HttpHelper.token = token;

            }
            catch (WebException ex)
            {
                var response = ex.Response as HttpWebResponse;
                if (response != null){
                    if (response.StatusCode == HttpStatusCode.NotFound)
                        return LoginResult.WRONG;
                    if (response.StatusCode == HttpStatusCode.Forbidden) ;
                    return LoginResult.NOAUTH;
                }
                throw new Exception(ex.Message);
            }
            return LoginResult.SUCCESS;
        }

        public static void logout()
        {
            HttpHelper.Delete(GeneralSetting.host + "tokens");
        }

        public static Boolean changePassword(string oldPwd, string newPwd)
        {
            return true;
        }
    }
}
