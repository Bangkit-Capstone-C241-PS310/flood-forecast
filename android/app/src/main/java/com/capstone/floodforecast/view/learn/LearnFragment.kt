package com.capstone.floodforecast.view.learn

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.floodforecast.R

class LearnFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_learn, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Contoh data untuk diisi ke dalam adapter
        val sampleData = listOf(
            LearnItem("Causes, Effects, and Prevention of Floods",
                "Raincoat Indonesia",
                "Floods are a recurring and devastating natural disaster that significantly impacts many regions around the world, particularly in countries like Indonesia with its vast archipelago and diverse geography. These phenomena can lead to severe environmental damage, economic disruption, and loss of human lives. Understanding the causes of floods, their far-reaching effects, and effective prevention strategies is crucial in mitigating their impact. This article explores the key factors contributing to flooding, examines the consequences on various aspects of society, and discusses practical measures to prevent and manage flood risks.\n" +
                        "\n" +
                        "Causes of Floods in Indonesia\n" +
                        "\n" +
                        "Floods in Indonesia are primarily caused by a combination of natural and human factors. Some of the key causes include:\n" +
                        "\n" +
                        "1. Heavy Rainfall: Indonesia's tropical climate and geography make it susceptible to heavy rainfall, which can lead to flooding. The country's mountainous regions can experience intense rainfall, causing rivers to overflow and flood the surrounding areas.\n" +
                        "2. Deforestation and Land Use Changes: Human activities such as deforestation and land use changes can contribute to flooding. The loss of vegetation cover and the creation of impermeable surfaces can increase runoff and reduce the ability of the land to absorb water, leading to flooding.\n" +
                        "3. Poor Infrastructure: Indonesia's rapid urbanization and inadequate infrastructure can exacerbate flooding. Clogged drains, inadequate drainage systems, and poorly constructed buildings can all contribute to flooding.\n" +
                        "4.  Climate Change: Climate change is also a significant factor in flooding in Indonesia. Rising sea levels, changes in precipitation patterns, and increased extreme weather events can all contribute to flooding.\n" +
                        "\n" +
                        "Effects of Floods in Indonesia\n" +
                        "\n" +
                        "Floods in Indonesia can have severe and long-lasting effects on the environment, economy, and human lives. Some of the key effects include:\n" +
                        "\n" +
                        "1. Environmental Damage: Floods can cause significant environmental damage, including soil erosion, water pollution, and loss of biodiversity.\n" +
                        "2. Economic Losses: Floods can result in substantial economic losses, including damage to infrastructure, loss of property, and disruption to businesses and industries.\n" +
                        "3. Human Health Risks: Floods can pose significant health risks, including the spread of waterborne diseases, mental health issues, and increased vulnerability to other health problems.\n" +
                        "4. Displacement and Social Impacts: Floods can lead to displacement, social unrest, and long-term social impacts, including the loss of livelihoods and community disruption.\n" +
                        "\n" +
                        "Prevention and Mitigation Strategies\n" +
                        "\n" +
                        "Preventing and mitigating floods in Indonesia requires a multi-faceted approach that involves government, civil society, and individual actions. Some of the key strategies include:\n" +
                        "\n" +
                        "1. Infrastructure Development: Improving infrastructure, including drainage systems, flood defenses, and water management systems, can help mitigate the impact of floods.\n" +
                        "2. Sustainable Land Use: Encouraging sustainable land use practices, such as reforestation and conservation, can help reduce the risk of flooding.\n" +
                        "3. Climate Change Adaptation: Implementing climate change adaptation measures, such as sea walls and flood-resistant construction, can help reduce the impact of flooding.\n" +
                        "4. Public Awareness and Education: Raising public awareness and education about flood risks and prevention strategies can help individuals and communities prepare for and respond to floods.\n" +
                        "5. Emergency Response and Preparedness: Developing effective emergency response plans and ensuring preparedness can help minimize the impact of floods.\n" +
                        "\n" +
                        "Conclusion\n" +
                        "\n" +
                        "Floods in Indonesia are a significant and complex issue that requires a comprehensive approach to prevention and mitigation. Understanding the causes, effects, and prevention strategies is crucial for mitigating the impact of floods in Indonesia. By working together, government, civil society, and individuals can reduce the risk of flooding and ensure a safer and more resilient future for the country.\n" +
                        "\n" +
                        "References\n" +
                        "\n" +
                        "[1] Faradiba, F. (2020). The Impact of Climate Factors, Disaster, and Social Community in Rural Development. The Journal of Asian Finance, Economics and Business, 7(9), 707–717.\n" +
                        "\n" +
                        "[2] Muliaty, M. (2021). INDONESIA'S FLOODING ISSUES. Muliaty Lecturer, Politeknik Negeri Media Kreatif, Makassar, Indonesia.\n" +
                        "\n" +
                        "[3] Australian National University. (n.d.). The socio-economic impacts of floods on Jakarta.\n" +
                        "\n" +
                        "[4] Jakarta Provincial Government. (n.d.). Jakarta Floods: Gov't Makes Emergency Responses, Flood Mitigation Efforts.\n" +
                        "\n" +
                        "[5] Smart City Jakarta. (n.d.). Jakarta Provincial Government's Commitment to Prevent Flooding.\n"
            ),
            LearnItem("Understanding Flood Risks", "Raincoat Indonesia", "Detailed article content about Understanding Flood Risks."),
            LearnItem("Flood Preparedness", "Raincoat Indonesia", "Detailed article content about Flood Preparedness.")
        )

        val adapter = LearnAdapter(sampleData) { item ->
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("EXTRA_TITLE", item.title)
                putExtra("EXTRA_AUTHOR", item.author)
                putExtra("EXTRA_TEXT_ARTICLE", item.textArticle) // Kirim text_article
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        return view
    }
}