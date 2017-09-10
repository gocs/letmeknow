using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.IO;
using System.Windows.Media.Imaging;

namespace letmeknow_admin
{
    class HttpHelper
    {
        private static CookieContainer cookie = new CookieContainer();
        public static string token;

        public static string Post(string url, string postStr, out HttpStatusCode code)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            //request.Headers["X-AUTH"] = token;
            request.Method = "POST";
            request.ContentType = "application/x-www-form-urlencoded";
            request.ContentLength = Encoding.UTF8.GetByteCount(postStr);
            request.CookieContainer = cookie;
            Stream myRequestStream = request.GetRequestStream();
            StreamWriter myStreamWriter = new StreamWriter(myRequestStream, Encoding.GetEncoding("gb2312"));
            myStreamWriter.Write(postStr);
            myStreamWriter.Close();

            HttpWebResponse response = (HttpWebResponse)request.GetResponse();

            response.Cookies = cookie.GetCookies(response.ResponseUri);
            Stream myResponseStream = response.GetResponseStream();
            StreamReader myStreamReader = new StreamReader(myResponseStream, Encoding.GetEncoding("utf-8"));
            string retStr = myStreamReader.ReadToEnd();
            code = response.StatusCode;
            myStreamReader.Close();
            myResponseStream.Close();
            return retStr;
        }

        public static string Post(string url, Dictionary<string, string> dataList, out HttpStatusCode code)
        {
            string postStr = "";
            foreach (KeyValuePair<string, string> i in dataList)
            {
                if (postStr != "")
                {
                    postStr += "&";
                }
                postStr += i.Key + "=" + i.Value;
            }
            return Post(url, postStr, out code);
        }

        public static string Post(string url, Dictionary<string, string> dataList)
        {
            HttpStatusCode code;
            return Post(url, dataList, out code);
        }

        public static string Post(string url)
        {
            HttpStatusCode code;
            return Post(url, "", out code);
        }

        public static string Get(string url, string getStr)
        {
            var myResponseStream = GetStream(url, getStr);
            StreamReader myStreamReader = new StreamReader(myResponseStream, Encoding.GetEncoding("utf-8"));
            string retStr = myStreamReader.ReadToEnd();
            myStreamReader.Close();
            myResponseStream.Close();

            return retStr;
        }

        public static string Get(string url)
        {
            return Get(url, "");
        }

        public static Stream GetStream(string url, string getStr)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url + (getStr == "" ? "" : "?") + getStr);
            request.Headers["X-AUTH"] = token;
            request.Method = "GET";
            request.ContentType = "text/html;charset=UTF-8";
            request.CookieContainer = cookie;

            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            return response.GetResponseStream();
        }

        public static string Get(string url, Dictionary<string, string> dataList)
        {
            string getStr = "";
            foreach (KeyValuePair<string, string> i in dataList)
            {
                if (getStr != "")
                {
                    getStr += "&";
                }
                getStr += i.Key + "=" + i.Value;
            }
            return Get(url, getStr);
        }

        public static string Put(string url)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            request.Headers["X-AUTH"] = token;
            request.Method = "PUT";
            request.ContentType = "text/html;charset=UTF-8";
            request.CookieContainer = cookie;

            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            var myResponseStream = response.GetResponseStream();
            StreamReader myStreamReader = new StreamReader(myResponseStream, Encoding.GetEncoding("utf-8"));
            string retStr = myStreamReader.ReadToEnd();
            myStreamReader.Close();
            myResponseStream.Close();

            return retStr;
        }

        public static string Delete(string url)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            request.Headers["X-AUTH"] = token;
            request.Method = "DELETE";
            request.ContentType = "text/html;charset=UTF-8";
            request.CookieContainer = cookie;

            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            var myResponseStream = response.GetResponseStream();
            StreamReader myStreamReader = new StreamReader(myResponseStream, Encoding.GetEncoding("utf-8"));
            string retStr = myStreamReader.ReadToEnd();
            myStreamReader.Close();
            myResponseStream.Close();

            return retStr;
        }

        public static BitmapImage getImage(string url)
        {
            if (url == null) return null;
            var re = new BitmapImage();
            var ss = HttpHelper.GetStream(GeneralSetting.host + "../" + url, "");
            re.BeginInit();
            re.StreamSource = ss;
            re.DownloadCompleted += (sender, e) =>
                re.StreamSource.Close();
            re.EndInit();
            return re;
        }
    }
}
