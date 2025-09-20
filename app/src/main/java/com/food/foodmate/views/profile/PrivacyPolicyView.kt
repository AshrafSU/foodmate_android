package com.food.foodmate.views.profile.privacyPolicyView

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.food.foodmate.ui.theme.FoodMateTheme
import com.food.foodmate.views.customViews.pageHeader.PageHeader

@Composable
fun PrivacyPolicyView(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        PageHeader(title = "Privacy Policy") { navController.popBackStack() }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {


            Text("1. Information We Collect\n\n" +
                    "- Personal Information: Name, email, phone number, and delivery address when you register or place an order.\n" +
                    "- Order Information: Details of your food orders, payment preferences, and transaction history.\n" +
                    "- Device & Usage Data: Information about your device, IP address, and interactions with the app for analytics.\n" +
                    "- Location Data (if permitted): To suggest nearby restaurants and improve delivery services.\n\n", fontWeight = FontWeight.SemiBold)

            Text("2. How We Use Your Information\n\n" +
                    "- Process and deliver your food orders.\n" +
                    "- Improve user experience and app performance.\n" +
                    "- Provide customer support and send order notifications.\n" +
                    "- Personalize restaurant recommendations.\n" +
                    "- Ensure security and prevent fraudulent activities.\n\n", fontWeight = FontWeight.SemiBold)

            Text("3. Sharing Your Information\n\n" +
                    "We do not sell or rent your personal data. However, we may share necessary details with:\n" +
                    "- Restaurants & Delivery Partners to process and deliver your orders.\n" +
                    "- Payment Processors to securely handle transactions.\n" +
                    "- Legal Authorities when required by law for security and compliance.\n\n", fontWeight = FontWeight.SemiBold)

            Text("4. Cookies & Analytics\n\n" +
                    "- We use analytics tools to understand app usage and improve performance.\n" +
                    "- Cookies or similar technologies may be used to enhance user experience.\n\n", fontWeight = FontWeight.SemiBold)

            Text("5. Your Choices & Rights\n\n" +
                    "- You can update or delete your personal information from your account settings.\n" +
                    "- You may opt out of marketing communications.\n" +
                    "- You can disable location access in your device settings.\n\n", fontWeight = FontWeight.SemiBold)

            Text("6. Security Measures\n\n" +
                    "We take reasonable steps to protect your data from unauthorized access, misuse, or disclosure. However, no system is 100% secure.\n\n", fontWeight = FontWeight.SemiBold)

            Text("7. Contact Us\n\n" +
                    "If you have any questions about this Privacy Policy, contact us at:\n" +
                    "📧 support@foodmate.com\n\n", fontWeight = FontWeight.SemiBold)

            Text("By using Food Mate, you agree to this Privacy Policy.", fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrivacyPolicyViewPreview() {
    FoodMateTheme {
        PrivacyPolicyView(navController = rememberNavController())
    }
}