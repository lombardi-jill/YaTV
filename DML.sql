
-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 27, 2020 at 07:29 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.9

START TRANSACTION;

--
-- Database: `YaTV`
--

--
-- Dumping data for table `App`
--

INSERT INTO `App` (`Name`, `Description`) VALUES
('Amazon Prime Video', 'Enjoy exclusive Amazon Originals as well as popular movies and TV shows. Watch anytime, anywhere. Start your free trial.'),
('Crunchyroll', 'Crunchyroll. World\'s Largest Collection of Anime. Catch your favorite shows and movies. Play Store · Try Premium For Free. Instant Access-No ads-Offline'),
('Disney+', 'Disney+, Hulu, and ESPN+ for just $12.99/month Bundle and save. Get the full Disney+ experience, plus your favorite TV episodes from Hulu (ad-supported), and exclusive shows and events from ESPN+.'),
('HBO Max', 'Stream all of HBO together with even more from Warner Bros., DC, Studio Ghibli, and more. Start your HBO Max free trial and get instant access on your favorite devices. Critically Acclaimed Docs. Emmy® Award-Winning Shows. Commercial Free. Exclusive Originals.'),
('HBOGO', 'With the HBO® app, enjoy instant and unlimited access to every episode of every season of the best HBO shows, movies, comedy, sports, and documentaries.'),
('Hulu', 'Watch TV shows and movies online. Stream TV episodes of Grey\'s Anatomy, This Is Us, Bob\'s Burgers, Brooklyn Nine-Nine, Empire, SNL, and popular movies'),
('Netflix', 'Watch Netflix movies & TV shows online or stream right to your smart TV, game console, PC, Mac, mobile, tablet and more.'),
('Sling TV', 'Sling is the live TV you love for less. Customize your channel lineup and watch TV online with no setup, hidden fees or long-term contracts.'),
('Twitch', 'Twitch is the world\'s leading live streaming platform for gamers and the things we love. Watch and chat now with millions of other fans from around the world.'),
('Youtube', 'Enjoy the videos and music you love, upload original content, and share it all with friends, family, and the world on YouTube');

--
-- Dumping data for table `Platform`
--

INSERT INTO `Platform` (`Name`, `IsMobile`) VALUES
('Amazon FireStick', 0),
('Android', 1),
('Apple App Store', 1),
('Apple TV', 0),
('Google Chromecast', 0),
('Portal TV', 0),
('Roku', 0),
('Samsung Smart TV', 0),
('TiVo', 0),
('TIZEN', 0);

--
-- Dumping data for table `AvailableOn`
--

INSERT INTO `AvailableOn` (`AppName`, `Platform`, `Version`, `Rating`) VALUES
('Amazon Prime Video', 'Amazon FireStick', '6.4.1', 3.9),
('Crunchyroll', 'Android', '5.6.1', 3.6),
('Crunchyroll', 'Apple App Store', '4.0', 4.8),
('Disney+', 'Samsung Smart TV', '3.2.0', 3.9),
('Disney+', 'TIZEN', '3.2.1', 3.5),
('Hulu', 'Amazon FireStick', '11.1.3', 4.7),
('Hulu', 'Android', '11.0.1', 4),
('Netflix', 'Android', '12.4.0', 4.5),
('Netflix', 'Apple App Store', '12.4.1', 4.3),
('Netflix', 'Roku', '12.4.1', 4.4),
('Sling TV', 'Portal TV', '1.2.9', 3.2),
('Twitch', 'Samsung Smart TV', '4.9.2', 4.9),
('Twitch', 'TiVo', '2.2.4', 4.1),
('Youtube', 'Apple App Store', '12.4.0', 4.5),
('Youtube', 'Google Chromecast', '1.3.6', 3.7);

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`Email`, `FirstName`, `LastName`, `Password`, `PassSalt`, `Country`) VALUES
('dorsey.jorge@hotmail.com', 'Jorge', 'Dorsey', 'f9689c4bb4277635f5ad7e6583a1dd1f70ba5e32e528836cee5462e58fc42c71', 'a4ef8f1a86', 'Peru'),
('ebrowncarley@cox.net', 'Emma', 'Brown-Carley', '5034ee71603cf2a92912c83427dd06715c18b99244b50c08b99d154c32a55659', '371dab3305', 'Italy'),
('green.ka@northeastern.edu', 'Katie', 'Green', 'ce35f3ac4a2567d395e43a3719dc72ad3b2572df8ce4f45c823534155a729f22', 'b6c05483f9', 'England'),
('hoffart.l@gmail.com', 'Lillian', 'Hoffart', 'e8407061a178bc08b34f445b74b6175ead027f53509dbb3d0fcf6cb351d20f9b', '5b142d89d3', 'England'),
('jill.lombardi@yahoo.com', 'Jillian', 'Lombardi', '6af6038bd4ed3a7a7b8228267685854b4802f7b95cec78249b2f7fc70370b09c', 'ef07733a2e', 'Italy'),
('kai-gp@hotmail.com', 'Kai Daniel', 'Gravel-Pucillo', 'a36b5987a38bc020e4f4d94e4f5fd42164f4796e9db96ff7440bee0a3657e16b', 'b696b2d6ee', 'USA'),
('kellie.laflin.26@gmail.com', 'Kellie', 'Laflin', '65f1726f5b889e0954489ae3db3c7372ddffc6ad309c29cf916af4f97b4e475f', '4d44f5413f', 'USA'),
('mel.purcell@live.com', 'Melissa', 'Purcell', '52c5258ef695c4d88b66cf6297d5200b797c7635fd6115168ad559c5cf2fa006', '8b82e37d83', 'Italy'),
('q.dhanani@northeastern.edu', 'Qanita', 'Dhanani', '025375b33716a112d46311f018811983cf68a749580cb773dc05bdb8651f47db', 'dbb635cb00', 'Malaysia'),
('rosa.sam@msn.com', 'Samantha', 'Rosa', '4aaebc95b202c8e7a275959b22ad2a135b2900baafe8cc87fa4a08ed51a6b9d9', '906fce88b1', 'Malaysia');

--
-- Dumping data for table `Subscription`
--

INSERT INTO `Subscription` (`UserEmail`, `AppName`, `ExpirationDate`, `Cost`) VALUES
('dorsey.jorge@hotmail.com', 'Disney+', '2020-12-20', 13.99),
('dorsey.jorge@hotmail.com', 'Netflix', '2020-12-20', 13.99),
('dorsey.jorge@hotmail.com', 'Youtube', '2021-02-01', 47.96),
('ebrowncarley@cox.net', 'Disney+', '2021-01-01', 12.99),
('ebrowncarley@cox.net', 'Netflix', '2021-01-01', 12.99),
('green.ka@northeastern.edu', 'Hulu', '2022-01-12', 5.99),
('green.ka@northeastern.edu', 'Netflix', '2021-01-12', 27.98),
('hoffart.l@gmail.com', 'Twitch', '2021-07-03', 119.88),
('jill.lombardi@yahoo.com', 'Amazon Prime Video', '2021-01-01', 12.99),
('jill.lombardi@yahoo.com', 'Disney+', '2021-01-01', 12.99),
('kai-gp@hotmail.com', 'Amazon Prime Video', '2021-12-30', 179.88),
('kai-gp@hotmail.com', 'HBO Max', '2021-12-30', 179.88),
('kai-gp@hotmail.com', 'HBOGO', '2020-12-30', 14.99),
('kellie.laflin.26@gmail.com', 'Disney+', '2020-12-20', 13.99),
('kellie.laflin.26@gmail.com', 'Netflix', '2020-12-20', 13.99),
('mel.purcell@live.com', 'Disney+', '2020-12-20', 13.99),
('mel.purcell@live.com', 'Netflix', '2020-12-20', 13.99),
('q.dhanani@northeastern.edu', 'Crunchyroll', '2021-06-27', 71.91),
('q.dhanani@northeastern.edu', 'Hulu', '2020-12-06', 5.99),
('rosa.sam@msn.com', 'Amazon Prime Video', '2020-11-30', 8.99),
('rosa.sam@msn.com', 'Crunchyroll', '2020-12-30', 12.99),
('rosa.sam@msn.com', 'Disney+', '2020-11-30', 8.99),
('rosa.sam@msn.com', 'HBO Max', '2020-11-30', 8.99),
('rosa.sam@msn.com', 'Twitch', '2021-11-30', 8.99);

--
-- Dumping data for table `Shows`
--

INSERT INTO `Shows` (`Id`, `Title`, `Description`) VALUES
(1, 'Schitt\'s Creek', 'Suddenly broke, the formerly filthy-rich Rose family is reduced to living in ramshackle motel in a town they once bought as a joke; Schitt\'s Creek.'),
(2, 'Friends', 'The misadventures of 20-30 year old friends in New York City: Joey a struggling actor, Monica a chef, Rachel a waitress who hopes to work in fashion, Ross a paleontologist, Chandler who hates his job in data processing, and Phoebe a masseuse/musician.'),
(3, 'Game of Thrones', 'Summers span decades. Winters can last a lifetime. And the struggle for the Iron Throne begins. Based on the bestselling book series by George R.R. Martin and created by David Benioff and D.B. Weiss.'),
(4, 'The Office', 'The Office is an American mockumentary sitcom television series that depicts the everyday work lives of office employees in the Scranton, Pennsylvania branch of the fictional Dunder Mifflin Paper Company.'),
(5, 'Stranger Things', 'When a young boy vanishes, a small town uncovers a mystery involving secret experiments, terrifying supernatural forces and one strange little girl.'),
(6, 'Attack On Titan', 'With his hometown in ruins, young Eren Yeager becomes determined to fight back against the giant Titans that threaten to destroy the human race.'),
(7, 'Firefly', 'The Crew of a starship travel through outer space after Earth\'s resources have diminished and forced us to look elsewhere in 2517. Captain Malcolm \"Mal\" Reynolds (Nathan Fillion) steers his bandit-crew through anything to keep them alive, and keep flying.'),
(8, 'The Mandalorian', 'After the stories of Jango and Boba Fett, another warrior emerges in the Star Wars universe. “The Mandalorian” is set after the fall of the Empire and before the emergence of the First Order. We follow the travails of a lone gunfighter in the outer reaches of the galaxy, far from the authority of the New Republic.'),
(9, 'Doctor Who', 'Adventures in Space and Time.'),
(10, 'Love Island USA', 'LOVE ISLAND is the sizzling summer series based on the international smash hit and cultural phenomenon. The matchmaking begins as a group of single “Islanders” come together in a stunning villa in Las Vegas, ready to embark on a summer of dating, romance, and ultimately, relationships. Every few days the Islanders pair up and those who are not coupled are at risk of being dumped from the island. Islanders are on the lookout for romance, but the road to love doesn\'t always run smoothly. Challenges abound with intriguing new Islander arrivals and dramatic twists as friendships and relationships form. In addition to choosing their partners wisely, Islanders must also win the hearts of viewers who have the opportunity to shape events on screen and ultimately crown one lucky couple the winner who will then have the chance to walk away with both love and the cash prize.'),
(11, 'Archer', 'Covert black ops and espionage take a back seat to zany personalities and relationships between secret agents and drones.'),
(12, 'South Park', 'Follows the misadventures of four irreverent grade-schoolers in the quiet, dysfunctional town of South Park, Colorado.');

--
-- Dumping data for table `Videos`
--

INSERT INTO `Videos` (`Id`, `Title`, `Description`, `AppHostedOn`, `ReleaseDate`, `Seconds`, `IsFree`) VALUES
(52, 'Clue', 'Six guests are anonymously invited to a strange mansion for dinner, but after their host is killed, they must cooperate with the staff to identify the murderer as the bodies pile up.', 'Amazon Prime Video', '1985-12-13', 5820, 0),
(53, 'Star Wars: Episode IV - A New Hope', 'Young farm boy Luke Skywalker is thrust into a galaxy of adventure when he intercepts a distress call from the captive Princess Leia. The event launches him on a daring mission to rescue her from the clutches of Darth Vader and the Evil Empire.', 'Disney+', '1977-05-25', 7500, 0),
(54, 'Star Wars: Episode V - The Empire Strikes Back', 'The Rebels scatter after the Empire attacks their base on the ice planet Hoth. Han Solo and Princess Leia are pursued by Imperials, while Luke trains with Jedi Master Yoda. Like must battle Darth Vader and learns the shocking truth of his past.', 'Disney+', '1980-06-20', 7620, 0),
(55, 'Star Wars: Episode VI - Return of the Jedi', 'The Empire prepares to crush the Rebellion with a more powerful Death Star. The Rebel fleet counters with a massive attack on the space station. Luke Skywalker confronts Darth Vader in a final climactic duel', 'Disney+', '1983-05-25', 8160, 0),
(56, 'Pride and Prejudice', 'Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?', 'Netflix', '2005-11-11', 8100, 1),
(57, 'Episode 1', 'The sizzling summer of love---and drama---is officially underway! Join host Arielle Vandenberg as she welcomes 10 sexy single Islanders to their beautiful beachside Villa. It doesn\'t take long for them to couple up, but when a surprise Islander moves in, ready to turn heads, someone might go home before they have time to unpack.', 'Sling TV', '2019-07-09', 5400, 1),
(58, 'Episode 2', 'After Kyra choses the guy she wants to couple up with, one female Islander is left riding solo while some of the other pairs start to rethink their choices.', 'Sling TV', '2019-07-10', 2700, 1),
(59, 'Rose', 'Rose Tyler meets a mysterious stranger called the Doctor, and her life will never be the same again. Soon, she realizes that her mom, her boyfriend, and the whole of Planet Earth are in danger. The only hope for salvation lies inside a strange blue police box...', 'Amazon Prime Video', '2005-03-26', 2700, 1),
(60, 'The End of the World', 'The Doctor takes Rose on her first voyage through time, to the year Five Billion. The Sun is about to expand and swallow the Earth. But amongst the alien races gathering to watch on Platform One, a murderer is at work. Who is controlling the mysterious, deadly spiders?', 'Amazon Prime Video', '2005-04-02', 2700, 0),
(61, 'New Earth', 'The Doctor and Rose board the Tardis for new adventures in time and space.', 'Amazon Prime Video', '2006-04-15', 2700, 0),
(62, 'Tooth and Claw', 'The Doctor and Rose have to protect Queen Victoria, but can anything stop the Empire of the Wolf?', 'Amazon Prime Video', '2006-04-22', 2700, 0),
(63, 'Smith and Jones', 'For Martha Jones, a medical student in Central London, an ordinary day turns into a nightmare when her entire hospital is transported to the Moon.', 'Amazon Prime Video', '2007-03-31', 2700, 0),
(64, 'The Shakespeare Code', 'For Martha\'s first trip in the Tardis, the Doctor takes her back in time, to Elizabethan England.', 'Amazon Prime Video', '2007-04-07', 2700, 0),
(65, 'Partners in Crime', 'Donna Noble is determined to find the Doctor again - even if it means braving the villainous Miss Foster and her hordes of sinister Adipose.', 'Amazon Prime Video', '2008-04-05', 2700, 0),
(66, 'The Fires of Pompeii', 'The Doctor and Donna travel back into ancient history.', 'Amazon Prime Video', '2008-04-12', 2700, 0),
(67, 'The Eleventh Hour', 'The Doctor has regenerated into a brand-new man, but danger strikes before he can recover, as Doctor Who returns for a new series.', 'Amazon Prime Video', '2010-04-03', 2700, 0),
(68, 'The Beast Below', 'The Doctor takes Amy to the distant future, where she can find Britain in space.', 'Amazon Prime Video', '2010-04-10', 2700, 0),
(69, 'The Impossible Astronaut', 'Four envelopes, numbered 2, 3, and 4, each containing a date, time, and map reference, unsigned but TARDIS blue.', 'Amazon Prime Video', '2011-04-23', 2700, 0),
(70, 'Day of the Moon', 'The Doctor is locked in the perfect prison.', 'Amazon Prime Video', '2011-04-30', 2700, 0),
(71, 'Asylum of the Daleks', 'Kidnapped by his oldest foe, the Doctor is forced on an impossible mission - to a place even the Daleks are too terrified to enter… the Asylum.', 'Amazon Prime Video', '2012-09-01', 2700, 0),
(72, 'Dinosaurs on a Spaceship', 'An unmanned spaceship hurtles towards the certain destruction - unless the Doctor can save it, and its impossible cargo… of dinosaurs!', 'Amazon Prime Video', '2012-09-08', 2700, 0),
(73, 'Borat Subsequent Moviefilm', 'Delivery of Prodigious Bribe to American Regime for make Benefit Once Glorious Nation of Kazakhstan', 'Amazon Prime Video', '2020-10-23', 5760, 0),
(74, 'Chapter 1: The Mandalorian', 'A Mandalorian bounty hunter tracks a target for a well-paying, mysterious client.', 'Disney+', '2019-11-12', 2340, 0),
(75, 'Chapter 2: The Child', 'Target in hand, the Mandalorian must now contend with scavengers.', 'Disney+', '2019-11-15', 1860, 0),
(76, 'Serenity', 'Malcolm Reynolds is a veteran and the captain of Serenity. He and his crew are smuggling goods, but they need to pick up some passengers for extra money. However, not all the passengers are what they seem.', 'Disney+', '2002-12-20', 2340, 0),
(77, 'The Train Job', 'Mal has second thoughts after discovering that two boxes of Alliance goods his crew has been hired to steal are full of badly needed medical supplies headed for the mining town of Paradiso.', 'Disney+', '2002-09-20', 2520, 0),
(78, 'The One with the Sonogram at the End', 'Ross finds out his ex-wife is pregnant. Rachel returns her engagement ring to Barry. Monica becomes stressed when her and Ross\'s parents come to visit.', 'HBO Max', '1994-09-22', 1380, 0),
(79, 'The One with the Thumb', 'Monica becomes irritated when everyone likes her new boyfriend more than she does. Chandler resumes his smoking habit. Phoebe is given $7000 when she finds a thumb in a can of soda.', 'HBO Max', '1994-10-06', 1380, 0),
(80, 'Our Cup Runneth Over', 'After losing everything, the Roses are forced to move to their only remaining asset, the town of Schitt\'s Creek.', 'Netflix', '2015-01-13', 1260, 0),
(81, 'The Drip', 'Johnny tries to get permission to sell the town; David and Alexis spend a night out with the locals.', 'Netflix', '2015-01-13', 1260, 0),
(82, 'Winter Is Coming', 'Eddard Stark is torn between his family and an old friend when asked to serve at the side of King Robert Baratheon; Viserys plans to wed his sister to a nomadic warlord in exchange for an army.', 'HBO Max', '2011-04-17', 3720, 0),
(83, 'The North Remembers', 'Tyrion arrives at King\'s Landing to take his father\'s place as Hand of the King. Stannis Baratheon plans to take the Iron Throne for his own. Robb tries to decide his next move in the war. The Night\'s Watch arrive at the house of Craster.', 'HBO Max', '2012-04-01', 3180, 0),
(84, 'Diversity Day', 'Michael\'s off color remark puts a sensitivity trainer in the office for a presentation, which prompts Michael to create his own.', 'Netflix', '2005-03-29', 1260, 0),
(85, 'The Dundies', 'Very much unlike his staff, an overeager Michael can\'t wait for this year\'s annual Dundies awards.', 'Netflix', '2005-09-20', 1200, 0),
(86, 'Chapter One: The Vanishing of Will Byers', 'At the U.S. Dept. of Energy an unexplained event occurs. Then when a young Dungeons and Dragons playing boy named Will disappears after a night with his friends, his mother Joyce and the town of Hawkins are plunged into darkness.', 'Netflix', '2016-07-15', 2880, 0),
(87, 'Chapter One: MADMAX', 'As the town preps for Halloween, a high-scoring rival shakes things up at the arcade, and a skeptical Hopper inspects a field of rotting pumpkins.', 'Netflix', '2017-10-27', 2880, 0),
(88, 'Chapter One: Suzie, Do You Copy?', 'Summer brings new jobs and budding romance. But the mood shifts when Dustin\'s radio picks up a Russian broadcast, and Will senses something is wrong.', 'Netflix', '2019-07-04', 3000, 0),
(89, 'Chasing Coral', 'In a realm of unearthly beauty, destruction hits close to home. A startling portrait of climate change under the sea.', 'Netflix', '2017-07-14', 5340, 0),
(90, 'To You, in 2000 Years: The Fall of Shiganshina, Part 1', 'Shiganshina, one of mankind forefront defenses against its biggest threat: the titans. Here the lives of Eren Yeager, Mikasa Ackerman and Armin Arlet are seemingly peaceful, but a sudden attack shows them the brutality of this world.', 'Crunchyroll', '2013-04-07', 1500, 0),
(91, 'Beast Titan', 'Coupled with the military\'s cover-up of a previous incident, the shocking discovery inside the wall causes a stir. Elsewhere, while the new Scout recruits are held for observation, a surprising threat appears.', 'Crunchyroll', '2017-04-01', 1500, 0),
(92, 'Smoke Signal', 'After barely surviving Eren\'s recovery, a rising threat from the shadows puts everyone\'s lives in danger again.', 'Crunchyroll', '2018-07-23', 1500, 0),
(93, 'Stephen Rips Up The Monologue', 'We all knew Donald Trump would do this, that he would refuse to show dignity in defeat. But what we didn\'t expect is how much it would hurt. Watch Stephen Colbert process his feelings in real time as he delivers a new monologue written in the wake of the President\'s sad, frightening remarks in the White House briefing room.', 'YouTube', '2020-11-06', 960, 1),
(94, 'Police: Last Week Tonight with John Oliver', 'As nationwide protests over the deaths of George Floyd and Breonna Taylor are met with police brutality, John Oliver discusses how the histories of policing and white supremacy are intertwined, the roadblocks to fixing things, and some potential paths forward.', 'YouTube', '2020-06-22', 2972, 1),
(95, 'UFC Golf Battle', 'Who knew combining sports could be so fun! Thanks to Xbox Game Pass Ultimate for sponsoring this video! Click HERE to check out Xbox Game ', 'YouTube', '2020-11-10', 1020, 1),
(96, 'Weight Gain 4000', 'The town prepares for an event involving Kathie Lee Gifford presenting an award to Cartman. He tries to lose weight but instead, becomes even more obese from a body gaining supplement called Weight Gain 4000. Meanwhile the boy\'s teacher, Mr. Garrison, attempts to assassinate Gifford.', 'Youtube', '1997-08-20', 1380, 1),
(97, 'Volcano', 'Stan\'s uncle Jimbo and his friend Ned take the four boys on a hunting trip in the mountains. Stan\'s father, a geologist, discovers that the mountain is a volcano about to erupt and convinces the townspeople to dig a trench for diverting the lava.', 'Youtube', '1997-08-27', 1401, 0),
(98, 'Mole Hunt', 'Archer goes to great lengths to cover up discrepancies in his ISIS expense account as he attempts to access the agency\'s mainframe computer. Cyril and Lana\'s Friday night dinner date is interrupted by work.', 'Hulu', '2009-09-17', 1740, 1),
(99, 'Training Day', 'Lana\'s residual lust for Archer prompts Malory to promote Cyril to field agent. Archer then trains his new competition as only he can, whilst Malory frets over her own past and her son\'s future.', 'Hulu', '2010-01-14', 1826, 0),
(100, 'Swiss Miss', 'In yet another attempt to secure funding, Malory drags her agents to the luxury winter resort town of Gstaad to protect a Swiss billionaire’s daughter.', 'Hulu', '2011-01-27', 1792, 0),
(101, 'A Going Concern', 'After Malory is wiped out in a Ponzi scheme, she hatches a scheme of her own: selling ISIS to rival spy agency ODIN. Archer and the rest of the ISIS employees must use every trick in the book to try to stop the sale.', 'Hulu', '2011-02-03', 1808, 0),
(102, 'Trolls World Tour', 'When the kingdom is invaded by Rock Trolls, Queen Poppy and Branch embark on an epic quest to find the harmony that will save them all.', 'Hulu', '2020-02-03', 3660, 0);

--
-- Dumping data for table `Tags`
--

INSERT INTO `Tags` (`VideoId`, `Tag`) VALUES
(52, 'classics'),
(52, 'comedy'),
(52, 'movies'),
(52, 'mystery'),
(53, 'movies'),
(53, 'sci-fi'),
(54, 'movies'),
(54, 'sci-fi'),
(55, 'movies'),
(55, 'sci-fi'),
(56, 'book adaptations'),
(56, 'historical drama'),
(56, 'movies'),
(59, 'sci-fi'),
(60, 'sci-fi'),
(61, 'sci-fi'),
(62, 'sci-fi'),
(63, 'sci-fi'),
(64, 'sci-fi'),
(65, 'sci-fi'),
(66, 'sci-fi'),
(67, 'sci-fi'),
(68, 'sci-fi'),
(69, 'sci-fi'),
(70, 'sci-fi'),
(71, 'sci-fi'),
(72, 'sci-fi'),
(74, 'sci-fi'),
(74, 'space western'),
(75, 'sci-fi'),
(75, 'space western'),
(76, 'sci-fi'),
(77, 'sci-fi'),
(78, 'comedy'),
(78, 'sitcom'),
(79, 'comedy'),
(79, 'sitcom'),
(80, 'comedy'),
(80, 'sitcom'),
(81, 'comedy'),
(81, 'sitcom'),
(82, 'drama'),
(83, 'drama'),
(84, 'comedy'),
(84, 'sitcom'),
(85, 'comedy'),
(85, 'sitcom'),
(86, 'sci-fi'),
(87, 'sci-fi'),
(88, 'sci-fi'),
(90, 'anime'),
(90, 'post-apocalyptic'),
(91, 'anime'),
(91, 'post-apocalyptic'),
(92, 'anime'),
(92, 'post-apocalyptic'),
(96, 'comedy'),
(97, 'comedy'),
(98, 'action'),
(99, 'action'),
(99, 'comedy'),
(100, 'comedy'),
(100, 'romance'),
(101, 'comedy'),
(101, 'drama');

--
-- Dumping data for table `Season`
--

INSERT INTO `Season` (`ShowId`, `SeasonNumber`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 8),
(2, 9),
(2, 10),
(3, 1),
(3, 2),
(3, 3),
(3, 4),
(3, 5),
(3, 6),
(3, 7),
(3, 8),
(4, 1),
(4, 2),
(5, 1),
(5, 2),
(5, 3),
(6, 1),
(6, 2),
(6, 3),
(7, 1),
(8, 1),
(9, 1),
(9, 2),
(9, 3),
(9, 4),
(9, 5),
(9, 6),
(9, 7),
(9, 8),
(9, 9),
(9, 10),
(9, 11),
(9, 12),
(10, 1),
(10, 2),
(11, 1),
(11, 2),
(12, 1);

--
-- Dumping data for table `Episode`
--

INSERT INTO `Episode` (`ShowId`, `SeasonNumber`, `EpisodeNumber`, `VideoId`) VALUES
(10, 1, 1, 57),
(10, 1, 2, 58),
(9, 1, 1, 59),
(9, 1, 2, 60),
(9, 2, 1, 61),
(9, 2, 2, 62),
(9, 3, 1, 63),
(9, 3, 2, 64),
(9, 4, 1, 65),
(9, 4, 2, 66),
(9, 5, 1, 67),
(9, 5, 2, 68),
(9, 6, 1, 69),
(9, 6, 2, 70),
(9, 7, 1, 71),
(9, 7, 2, 72),
(8, 1, 1, 74),
(8, 1, 2, 75),
(7, 1, 1, 76),
(7, 1, 2, 77),
(2, 1, 2, 78),
(2, 1, 3, 79),
(1, 1, 1, 80),
(1, 1, 2, 81),
(3, 1, 1, 82),
(3, 1, 2, 83),
(4, 1, 1, 84),
(4, 2, 1, 85),
(5, 1, 1, 86),
(5, 2, 1, 87),
(5, 3, 1, 88),
(6, 1, 1, 90),
(6, 2, 1, 91),
(6, 3, 1, 92),
(12, 1, 2, 96),
(12, 1, 3, 97),
(11, 1, 1, 98),
(11, 1, 2, 99),
(11, 2, 1, 100),
(11, 2, 2, 101);

--
-- Dumping data for table `WatchList`
--

INSERT INTO `WatchList` (`UserEmail`, `VideoId`, `IsLiked`) VALUES
('dorsey.jorge@hotmail.com', 74, 1),
('dorsey.jorge@hotmail.com', 88, 0),
('dorsey.jorge@hotmail.com', 97, 0),
('ebrowncarley@cox.net', 75, 1),
('ebrowncarley@cox.net', 87, 1),
('green.ka@northeastern.edu', 99, 1),
('green.ka@northeastern.edu', 57, 1),
('green.ka@northeastern.edu', 100, 0),
('jill.lombardi@yahoo.com', 52, 0),
('jill.lombardi@yahoo.com', 53, 1),
('jill.lombardi@yahoo.com', 67, 0),
('jill.lombardi@yahoo.com', 84, 1),
('kai-gp@hotmail.com', 52, 1),
('kai-gp@hotmail.com', 70, 0),
('kellie.laflin.26@gmail.com', 53, 1),
('kellie.laflin.26@gmail.com', 89, 1),
('mel.purcell@live.com', 53, 0),
('mel.purcell@live.com', 56, 1),
('mel.purcell@live.com', 77, 0),
('mel.purcell@live.com', 80, 1),
('q.dhanani@northeastern.edu', 90, 1),
('q.dhanani@northeastern.edu', 93, 1),
('rosa.sam@msn.com', 52, 0),
('rosa.sam@msn.com', 64, 0),
('rosa.sam@msn.com', 74, 1),
('rosa.sam@msn.com', 91, 1);

--
-- Dumping data for table `VideoList`
--

INSERT INTO `VideoList` (`UserEmail`, `VideoId`) VALUES
('dorsey.jorge@hotmail.com', 56),
('green.ka@northeastern.edu', 102),
('hoffart.l@gmail.com', 52),
('kai-gp@hotmail.com', 54),
('kellie.laflin.26@gmail.com', 53),
('kellie.laflin.26@gmail.com', 54),
('kellie.laflin.26@gmail.com', 55),
('kellie.laflin.26@gmail.com', 56),
('mel.purcell@live.com', 93),
('q.dhanani@northeastern.edu', 52);

--
-- Dumping data for table `ShowList`
--

INSERT INTO `ShowList` (`UserEmail`, `ShowId`) VALUES
('dorsey.jorge@hotmail.com', 7),
('ebrowncarley@cox.net', 4),
('ebrowncarley@cox.net', 8),
('green.ka@northeastern.edu', 5),
('hoffart.l@gmail.com', 7),
('hoffart.l@gmail.com', 9),
('jill.lombardi@yahoo.com', 2),
('jill.lombardi@yahoo.com', 3),
('jill.lombardi@yahoo.com', 6),
('kai-gp@hotmail.com', 1),
('kellie.laflin.26@gmail.com', 9),
('mel.purcell@live.com', 1),
('mel.purcell@live.com', 10),
('q.dhanani@northeastern.edu', 3),
('q.dhanani@northeastern.edu', 6),
('rosa.sam@msn.com', 2),
('rosa.sam@msn.com', 10);
COMMIT;
