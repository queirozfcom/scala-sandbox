import java.time.Instant

val str = """{
  |  "channel": "#storedash-alerts",
  |  "username": "storedash",
  |  "icon_emoji": ":storedashbot:",
  |  "attachments": [
  |    {
    |      "color": "$msgColor",
    |      "fallback": "Pricing alert",
    |      "thumb_url": "https://dl.dropboxusercontent.com/u/8015936/vtex/iconprice.png",
    |      "text": "Host: <$host> Account: $accountName\nPrice variation: $fmtPriceVariation%",
    |      "author_name": "Pricing",
    |      "fields": [
    |        {
      |          "title": "Order ID",
      |          "value": "<$transactionLink|$transaction>",
      |          "short": true
      |        },
    |        {
      |          "title": "Campaign",
      |          "value": "$fmtCampaignName",
      |          "short": true
      |        },
    |        {
      |          "title": "Expected Order Value",
      |          "value": "($fmtCurrency) $fmtExpectedPrice",
      |          "short": true
      |        },
    |        {
      |          "title": "Actual Order Value",
      |          "value": "($fmtCurrency) $fmtActualPrice",
      |          "short": true
      |        }
    |      ],
    |      "footer": "Click order id above for further analysis"
    |    }
  |  ]
  |}
|""".stripMargin

println(str)