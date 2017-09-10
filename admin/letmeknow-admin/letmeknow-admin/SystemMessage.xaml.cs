using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using letmeknow_admin.Services;
using MahApps.Metro.Controls;

namespace letmeknow_admin
{
    /// <summary>
    /// SystemMessage.xaml 的交互逻辑
    /// </summary>
    public partial class SystemMessage : MetroWindow
    {
        public SystemMessage()
        {
            InitializeComponent();
        }

        private void textBox_TextChanged(object sender, TextChangedEventArgs e)
        {
            /*MessageBox.Show("change");
            if (textBox.Text == string.Empty)
            {
                lblName.Content = string.Empty;
                return;
            }
            if (rbtnUser.IsChecked.Value)
            {
                var tUser = UserService.getUser(Convert.ToInt32(textBox.Text));
                if (tUser != null)
                    lblName.Content = tUser.username;
                else
                    lblName.Content = "没有此用户";
            }
            else if (rbtnGroup.IsChecked.Value)
            {
                var tGroup = GroupService.getGroup(Convert.ToInt32(textBox.Text));
                if (tGroup != null)
                    lblName.Content = tGroup.groupName;
                else
                    lblName.Content = "没有此通知组";
            }*/
        }

        private void textBox_PreviewTextInput(object sender, TextCompositionEventArgs e)
        {
            MessageBox.Show("input");
            Regex re = new Regex("^[0-9]*$");
            e.Handled = re.IsMatch(e.Text);
        }
    }
}
