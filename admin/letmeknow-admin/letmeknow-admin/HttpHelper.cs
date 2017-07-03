using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.IO;

namespace letmeknow_admin
{
    class HttpHelper
    {
        private static CookieContainer cookie = new CookieContainer();

        public static string Post(string url, string postStr)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
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
            myStreamReader.Close();
            myResponseStream.Close();
            return retStr;
        }

        public static string Post(string url, Dictionary<string, string> dataList)
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
            return Post(url, postStr);
        }

        public static string Get(string url, string getStr)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url + (getStr == "" ? "" : "?") + getStr);
            request.Method = "GET";
            request.ContentType = "text/html;charset=UTF-8";
            request.CookieContainer = cookie;

            HttpWebResponse response = (HttpWebResponse)request.GetResponse();
            Stream myResponseStream = response.GetResponseStream();
            StreamReader myStreamReader = new StreamReader(myResponseStream, Encoding.GetEncoding("utf-8"));
            string retStr = myStreamReader.ReadToEnd();
            myStreamReader.Close();
            myResponseStream.Close();

            return retStr;
        }

        public static Stream GetStream(string url, string getStr)
        {
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url + (getStr == "" ? "" : "?") + getStr);
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
    }
}
